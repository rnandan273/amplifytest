(ns cljapp.subs
  (:require
   [re-frame.core :as re-frame]))

(re-frame/reg-sub
 ::name
 (fn [db]
   (:name db)))

(re-frame/reg-sub
 ::view
 (fn [db]
   (:view db)))

(re-frame/reg-sub
 :common/route
 (fn [db _]
   (-> db :common/route)))

(re-frame/reg-sub
 :common/page-id
 :<- [:common/route]
 (fn [route _]
   (-> route :data :name)))

(re-frame/reg-sub
 :common/page
 :<- [:common/route]
 (fn [route _]
   (-> route :data :view)))

(re-frame/reg-sub
 :common/error
 (fn [db _]
   (:common/error db)))

(re-frame/reg-sub
 :error-msg
 (fn [db _]
   (:error-msg db)))
