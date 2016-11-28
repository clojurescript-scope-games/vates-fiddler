(ns vates-fiddler.cmpstockchart
    (:require [reagent.core :as reagent]))

;; (def chart-config
;;   {:rangeSelector {:selected 1},
;;    :title {:text "Stick Price"}
;;    :series [{:name 'BiatschTec'
;;              :data [[1259625600000,28.14]
;;                     [1259712000000,28.03]
;;                     [1259798400000,28.07]],
;;              :tooltip {:valueDecimals 2}}] })

(defn chart-config [data]
  {:rangeSelector {:selected 1},
   :title {:text "Stock Price"}
   :series [{:name 'BiatschTec'
             :data data,
             :tooltip {:valueDecimals 2}}] })

(defn cs-render []
  [:div {:style {:min-width "310px" :max-width "800px"
                 :height "400px" :margin "0 auto"}}])

(defn cs-did-mount [this]
  (js/Highcharts.stockChart. (reagent/dom-node this)
                             (clj->js ( chart-config [[1259625600000,28.14]
                                                      [1259712000000,28.03]
                                                      [1259798400000,28.07]]))))

(defn class[]
  (reagent/create-class {:reagent-render cs-render
                         :component-did-mount cs-did-mount}))
