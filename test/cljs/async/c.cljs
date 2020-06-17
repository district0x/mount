(ns async.c
  (:require [async.a :refer [a]]
            [full.async :refer [<? go-try]]
            [clojure.core.async :refer [timeout]])
  (:require-macros [mount.core :refer [defstate]]))

(defstate c
  :start (go-try
           (js/console.log "Starting state C")
           (<? (timeout 1000))
           #_(throw (js/Error "C Doesn't work"))
           (js/console.log "State C started.")
           (inc @a))
  :stop (do
          (js/console.log "Stopping state C")
          (js/console.log "State C stopped.")))
