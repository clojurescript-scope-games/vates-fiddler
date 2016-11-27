(ns vates-fiddler.core
    (:require [reagent.core :as reagent]
              [re-frame.core :as re-frame]
              [vates-fiddler.events]
              [vates-fiddler.subs]
              [vates-fiddler.routes :as routes]
              [vates-fiddler.views :as views]
              [vates-fiddler.config :as config]))


(defn dev-setup []
  (when config/debug?
    (enable-console-print!)
    (println "dev mode")))

(defn mount-root []
  (reagent/render [views/main-panel]
                  (.getElementById js/document "app")))

(defn ^:export init []
  (routes/app-routes)
  (re-frame/dispatch-sync [:initialize-db])
  (dev-setup)
  (mount-root))
