(ns cljapp.events
  (:require
   [re-frame.core :as re-frame]
   [reitit.frontend.easy :as rfe]
   [reitit.frontend.controllers :as rfc]
   [cljapp.db :as db]
   [day8.re-frame.tracing :refer-macros [fn-traced]]
   ))

(re-frame/reg-event-db
 ::initialize-db
 (fn-traced [_ _]
   db/default-db))

(re-frame/reg-event-db
 :view
(fn [db [_ data]]
  (.log js/console data)
    (assoc db :view data)))

(re-frame/reg-event-db
 :common/navigate
 (fn [db [_ match]]
   (let [old-match (:common/route db)
         new-match (assoc match :controllers
                          (rfc/apply-controllers (:controllers old-match) match))]
     (assoc db :common/route new-match))))

(re-frame/reg-fx
 :common/navigate-fx!
 (fn [[k & [params query]]]
   (rfe/push-state k params query)))

(re-frame/reg-event-fx
 :common/navigate!
 (fn [_ [_ url-key params query]]
   {:common/navigate-fx! [url-key params query]}))

(re-frame/reg-event-db
 :common/set-error
 (fn [db [_ error]]
   (assoc db :common/error error)))
