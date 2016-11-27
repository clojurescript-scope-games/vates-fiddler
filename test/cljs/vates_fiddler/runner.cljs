(ns vates-fiddler.runner
    (:require [doo.runner :refer-macros [doo-tests]]
              [vates-fiddler.core-test]))

(doo-tests 'vates-fiddler.core-test)
