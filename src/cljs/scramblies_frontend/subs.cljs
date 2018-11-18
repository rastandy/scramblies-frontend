(ns scramblies-frontend.subs
  (:require
   [re-frame.core :as re-frame]))

(re-frame/reg-sub
 :scramblies/str1
 (fn [db]
   (:scramblies/str1 db)))

(re-frame/reg-sub
 :scramblies/str2
 (fn [db]
   (:scramblies/str2 db)))

(re-frame/reg-sub
 :scramblies/result
 (fn [db]
   (get-in db [:scramblies/scramble-result :data])))

(re-frame/reg-sub
 :scramblies/error
 (fn [db]
   (get-in db [:scramblies/scramble-result :error])))
