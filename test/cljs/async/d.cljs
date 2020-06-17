(ns async.d
  (:require [async.b :refer [b]]
            [async.c :refer [c]]
            [full.async :refer [<? go-try]]
            [clojure.core.async :refer [timeout]])
  (:require-macros [mount.core :refer [defstate]]))


(defstate d
  :start (do
           (js/console.log "Starting state D")
           (js/console.log "Final result (should be 2) : " (+ @b @c))
           (js/console.log "State D started.")
           nil)
  :stop (do
          (js/console.log "Stopping state D")
          (js/console.log "State D stopped.")))
