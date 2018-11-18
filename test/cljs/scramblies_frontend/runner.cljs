(ns scramblies-frontend.runner
    (:require [doo.runner :refer-macros [doo-tests]]
              [scramblies-frontend.core-test]))

(doo-tests 'scramblies-frontend.core-test)
