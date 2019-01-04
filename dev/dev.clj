(ns dev
  (:use [clojure.pprint :only (pprint)])
  (:require [mount.core :as mnt]
            [clojure.tools.namespace.repl :as tn]
            [clojure.tools.namespace.find :as tn-find]))

(defn start
  "starts mount via mount/start"
  []
  (mnt/start)
  :mount-started)

(defn stop 
  "stops mount via mount/stop"
  []
  (mnt/start)
  :mount-stopped)

(defn refresh
  "refreshes the apps namespaces via tn/refresh"
  []
  (tn/refresh)
  :refreshed-namespaces)

(defn reload
  "stops mount, refreshes the app namespaces then calls (start) 
   via mnt/stop, tn/refresh, mnt/start"
  []
  (mnt/stop)
  (tn/refresh :after 'mnt/start)
  :mount-stopped-refreshed-namespaces-mount-started)

(defn exit
  "stops mount and exists the app via mnt/stop, System/exit 0"
  []
  (mnt/stop)
  (println "mount stopped, exiting the app...")
  (System/exit 0))

(defn- help-data-item
  "given a public intern mapping extract help data for user consumption/viewing"
  [public-intern]
  (let [intern-meta (-> public-intern 
                    val
                    meta)
         {:keys  [name, doc, arglists]} intern-meta]
    {:fn name :params arglists :doc doc}))

(defn help-data
  "given a namespace get help data (inspects public interns)"
  [an-ns] 
  (map help-data-item (ns-publics an-ns)))

(defn help "prints help data for the current ns"
  [] 
  (pprint (help-data *ns*)))

(defonce init
  (delay
    (do (println "initilising app...")
        (mnt/start))
    :initilised))

@init

