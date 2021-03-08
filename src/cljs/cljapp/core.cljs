(ns cljapp.core
  (:require
   [reagent.core :as reagent]
   [re-frame.core :as re-frame]
   [cljapp.events :as events]
   [cljapp.views :as views]
   [cljapp.config :as config]
   ["aws-amplify" :default Amplify :as amp]
   ["aws-amplify-react" :refer (withAuthenticator)]
  
   ))

(defn dev-setup []
  (when config/debug?
    (println "dev mode")))

(def root-view
  (reagent/adapt-react-class
   (withAuthenticator
    (reagent/reactify-component views/main-panel) true)))

(defn ^:dev/after-load mount-root []
  (re-frame/clear-subscription-cache!)
  (.configure Amplify (clj->js {
        :aws_project_region "us-east-1"
    :aws_cognito_identity_pool_id "us-east-1:26bb7360-5782-410c-ac8d-8c1d8141eded"
    :aws_cognito_region "us-east-1"
    :aws_user_pools_id "us-east-1_d6euJdpOM"
    :aws_user_pools_web_client_id "1ck7v16ttoej7bhvn4suq0ldnd"

        :oauth {}}))
  (re-frame/dispatch-sync [::events/initialize-db])
  (reagent.dom/render [root-view]
                  (.getElementById js/document "app")))

(defn init []
  (dev-setup)
  (mount-root))
