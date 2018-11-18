(ns scramblies-frontend.events
  (:require
   [re-frame.core :as re-frame]
   [scramblies-frontend.config :as conf]
   [scramblies-frontend.db :as db]
   [day8.re-frame.tracing :refer-macros [fn-traced defn-traced]]
   [day8.re-frame.http-fx]
   [ajax.core :as ajax]))


(re-frame/reg-event-db
 ::initialize-db
 (fn-traced [_ _]
   db/default-db))

(re-frame/reg-event-db
 :scramblies/input-change
 (fn-traced [db [_ input-key value]]
            (assoc db input-key value)))

(re-frame/reg-event-fx
 :scramblies/scramble
 (fn-traced [{:keys [db]} [_ str1 str2]]
            {:http-xhrio {:method          :get
                          :uri             (str conf/endpoint "?str1=" str1 "&str2=" str2)
                          :timeout         8000
                          :response-format (ajax/json-response-format {:keywords? true})
                          :on-success      [:scramblies/scramble-call-result]
                          :on-failure      [:scramblies/scramble-call-error]}}))

(re-frame/reg-event-db
 :scramblies/scramble-call-result
 (fn-traced [db [_ result]]
            (assoc db :scramblies/scramble-result result)))

(re-frame/reg-event-db
 :scramblies/scramble-call-error
 (fn-traced [db [_ error]]
            (assoc db :scramblies/scramble-result {:error error})))
