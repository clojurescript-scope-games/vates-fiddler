(ns vates-fiddler.types)

(defrecord stock-data [date open high low close volume]
  Object
  (toString [this] (str "#stock-data" (into {} this))))
