(ns vates-fiddler.views
    (:require [re-frame.core :as re-frame]
              [re-com.core :as re-com]
              [re-com.core   :refer [h-box v-box box gap line input-text input-textarea label checkbox radio-button slider title p]]
              [re-com.misc   :refer [input-text-args-desc]]
              [vates-fiddler.lorem :as lorem]))


;; home

(defn home-title []
  (let [name (re-frame/subscribe [:name])]
    (fn []
      [:div.h1
       [re-com/title
        :label (str "Hello from " @name ". This is the Home Page.")
        :level :level1]])))

(defn home-panel []
  [:div
   [:div.h1 "This is Home."]
   [:a.p1 {:href "#/assets"} "got to assets page "]
   [:a.p1  {:href "#/about"} "got to about page "]
   [:div (lorem/create-lorem "2p")]])

(defn about-panel []
  [:div
   [:div "This is the About Page."]
   [:a.btn.btn-outline {:href "#/"} "got to home page"]])

(defn assets-panel []
  [:div
   [:div "Assets"
    [:div.mb1 [:a {:href "#/"} "go to home page"]]]])

;; Main

(defmulti panels identity)
(defmethod panels :home-panel [] [home-panel])
(defmethod panels :about-panel [] [about-panel])
(defmethod panels :assets-panel [] [assets-panel])
(defmethod panels :default [] [home-panel])

(defn show-panel
  [panel-name]
  [panels panel-name])

(defn main-panel []
  (let [active-panel (re-frame/subscribe [:active-panel])]
    (fn []
      [re-com/v-box
       :height "100%"
       :children [[panels @active-panel]]])))
