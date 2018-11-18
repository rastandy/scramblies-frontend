(ns scramblies-frontend.views
  (:require
   [re-frame.core :as re-frame]
   [scramblies-frontend.subs :as subs]))


(defn main-panel []
  (let [str1 (re-frame/subscribe [:scramblies/str1])
        str2 (re-frame/subscribe [:scramblies/str2])]
    [:div
     [:h1 "Insert two strings below"]
     [:form
      [:label "String to scramble:"]
      [:input {:type "text", :name "str1", :value @str1
               :on-change #(re-frame/dispatch
                            [:scramblies/input-change :scramblies/str1 (.. % -target -value)])}]
      [:br]
      [:label "String to match:"]
      [:input {:type "text", :name "str2", :value @str2
               :on-change #(re-frame/dispatch
                            [:scramblies/input-change :scramblies/str2 (.. % -target -value)])}]
      [:br]
      [:input {:type "button"
               :value "Scramble"
               :on-click (fn [event]
                           (re-frame/dispatch
                            [:scramblies/scramble @str1 @str2]))}]]]))
