(ns async.c
  (:require [async.a :refer [a]]
            [clojure.core.async :refer [<! chan go timeout]])
  (:require-macros [mount.core :refer [defstate]]))

(defstate c
  :start (go
           (js/console.log "Starting state C")
           (<! (timeout 1000))
           (js/console.log "State C started.")
           (inc @a))
  :stop (do
          (js/console.log "Stopping state C")
          (js/console.log "State C stopped.")))
