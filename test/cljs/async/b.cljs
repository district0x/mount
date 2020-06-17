(ns async.b
  (:require [async.a :refer [a]]
            [full.async :refer [<? go-try]]
            [clojure.core.async :refer [timeout]])
  (:require-macros [mount.core :refer [defstate]]))

(defstate b
  :start (do
           (js/console.log "Starting state B")
           (js/console.log "State B started.")
           #_(throw (js/Error "B Doesn't work"))
           (inc @a))
  :stop (go-try
          (js/console.log "Stopping state B")
          (<? (timeout 1000))
          (js/console.log "State B stopped.")))
