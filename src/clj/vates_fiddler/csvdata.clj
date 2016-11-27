(ns vates-fiddler.csvdata
  (:require [clojure.data.csv :as csv]
            [clojure.java.io :as io]
            [clj-time.core :as clj-time]
            [clj-time.format :as tf]
            [clj-time.coerce :as tc]))

(def date-format (tf/formatter "YYYYMMDD HHmmss"))

(defrecord stock-data [date open high low close volume]
  Object
  (toString [this] (str "#stock-data" (into {} this))))

(defn coerce [f v d]
  (try
    (f v)
    (catch Exception e
      d)))

(defn ->double [v] (coerce #(Double/parseDouble %) v 0.0))
(defn ->long [v] (coerce #(Long/parseLong %) v 0))

(defn row->stock-data [row]
  (let [[date open high low close volume] row]
    (->stock-data (tc/to-long (tf/parse date-format date))
                  (->double open)
                  (->double high)
                  (->double low)
                  (->double close)
                  (->long volume))))

(defn read-stock-prices [filename]
  (with-open [f (io/reader filename)]
    (doall (map row->stock-data (csv/read-csv f :separator \;)))))
