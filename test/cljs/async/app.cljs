(ns async.app
  (:require [async.a]
            [async.b]
            [async.c]
            [async.d]
            [full.async :refer [<? go-try]]
            [clojure.core.async :refer [<! timeout]]
            [mount.core :as mount]))

(go-try

 (try
   (<? (mount/start))
   (catch js/Error e
     (js/console.error e)))

 (<? (timeout 1000))
 (js/console.log "\n\n Stopping all components! \n\n")
 (mount/stop))
