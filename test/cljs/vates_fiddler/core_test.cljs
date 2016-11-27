(ns vates-fiddler.core-test
  (:require [cljs.test :refer-macros [deftest testing is]]
            [vates-fiddler.core :as core]))

(deftest fake-test
  (testing "fake description"
    (is (= 1 2))))
