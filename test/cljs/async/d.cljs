(ns async.d
  (:require [async.b]
            [async.c]
            [clojure.core.async :refer [<! chan go timeout]])
  (:require-macros [mount.core :refer [defstate]]))


(defstate d
  :start (do
           (js/console.log "Starting state D")
           (js/console.log "State D started."))
  :stop (do
          (js/console.log "Stopping state D")
          (js/console.log "State D stopped.")))
