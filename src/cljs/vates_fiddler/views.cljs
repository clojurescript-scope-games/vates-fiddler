(ns vates-fiddler.views
    (:require [re-frame.core :as re-frame]
              [re-com.core :as re-com]
              [re-com.core   :refer [h-box v-box box gap line input-text input-textarea label checkbox radio-button slider title p]]
              [re-com.misc   :refer [input-text-args-desc]]
              [vates-fiddler.lorem :as lorem]
              [vates-fiddler.cmphighchart :as cmp-hc]
              [vates-fiddler.cmpstockchart :as cmp-sc]
              [reagent.core :as reagent]))

;; home

(defn home-panel []
  (let [name (re-frame/subscribe [:name])]
    (fn []
      [:div
       [:a.p1 {:href "#/assets"} "goto assets page "]
       [:a.p1  {:href "#/about"} "goto about page "]
       [:div.h1.p2 (str @name ", this is Home.")]
       [:div.p2 (lorem/create-lorem "2p")]])))

(defn about-panel []
  [:div
   [:a.p1 {:href "#/"} "goto home page"]
   [:div.p2 "This is the About Page."]])


(defn assets-panel []
  [:div
   [:a.p1 {:href "#/"} "gogo home page"]
   [:div [cmp-sc/class]]
   ;; [:div [cmp-hc/class]]
   ])


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
