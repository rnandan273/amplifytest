(ns cljapp.core
  (:require
   [reagent.core :as reagent]
   [reagent.dom :as rdom]
   [re-frame.core :as re-frame]
   [cljapp.events :as events]
   [cljapp.views :as views]
   [cljapp.config :as config]
   [reitit.core :as reitit]
   [reitit.frontend.easy :as rfe]
   ["aws-amplify" :default Amplify :as amp]))

(defn dev-setup []
  (when config/debug?
    (println "dev mode")))

(def root-view
  (reagent/adapt-react-class
  ; (withAuthenticator
   (reagent/reactify-component views/main-panel) ;true)
   ))

(defn page []
  (if-let [page1 @(re-frame/subscribe [:common/page])]
    [:div
     [views/navbar]
     [page1]]))


(defn navigate! [match _]
  (re-frame/dispatch [:common/navigate match]))

(def router
  (reitit/router
   [["/" {:name        :home
          :view        #'views/home-page
          :controllers [{:start (fn [_] (re-frame/dispatch [:page/init-home]))}]}]
    ["/login" {:name :login
               :view #'views/login-page}]
    ["/signup" {:name :signup
                :view #'views/signup-page}]
    ["/user-account-service-log" {:name :service-log
                                  :view #'views/service-log-page}]
    ["/user-account-services" {:name :services
                               :view #'views/services-page}]
    ["/about" {:name :about
               :view #'views/about-page}]]))

(defn start-router! []
  (rfe/start!
   router
   navigate!
   {}))


(defn ^:dev/after-load mount-root []
  (re-frame/dispatch-sync [::events/initialize-db])
  (re-frame/clear-subscription-cache!)
  (.configure Amplify (clj->js {:aws_project_region "us-east-1"
                                :aws_cognito_identity_pool_id "us-east-1:26bb7360-5782-410c-ac8d-8c1d8141eded"
                                :aws_cognito_region "us-east-1"
                                :aws_user_pools_id "us-east-1_d6euJdpOM"
                                :aws_user_pools_web_client_id "1ck7v16ttoej7bhvn4suq0ldnd"
                                :oauth {}}))

  (start-router!)
  (rdom/render [page] (.getElementById js/document "app")))

(defn init []

  (dev-setup)
  (mount-root))
