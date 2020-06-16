(ns async.app
  (:require [async.a]
            [async.b]
            [async.c]
            [async.d]
            [clojure.core.async :refer [<! chan go timeout]]
            [mount.core :as mount]))

(go
  (<! (mount/start))
  (<! (timeout 1000))
  (js/console.log "\n\n Stopping all components! \n\n")
  (mount/stop))
