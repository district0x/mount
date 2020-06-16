(ns async.b
  (:require [async.a]
            [clojure.core.async :refer [<! chan go timeout]])
  (:require-macros [mount.core :refer [defstate]]))

(defstate b
  :start (do
           (js/console.log "Starting state B")
           (js/console.log "State B started."))
  :stop (go
          (js/console.log "Stopping state B")
          (<! (timeout 1000))
          (js/console.log "State B stopped.")))
