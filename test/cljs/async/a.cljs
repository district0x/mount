(ns async.a
  (:require [mount.core :as mount]
            [full.async :refer [<? go-try]]
            [clojure.core.async :refer [timeout]])
  (:require-macros [mount.core :refer [defstate]]))

(defstate a
  :start (go-try
           (js/console.log "Starting state A")
           (<? (timeout 2000))
           (js/console.log "State A started.")
           0)
  :stop (do
          (js/console.log "Stopping state A")
          (js/console.log "State A stopped.")))
