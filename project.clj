(defproject alexa-spec "0.1.0"
  :description "Provides a Clojure spec for Amazon Alexa json payloads"
  :url "http://functionalcomposition.com"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :aliases {"clo" ["cloverage"]} ; type lein clo for lein cloverage
  :dependencies [[org.clojure/clojure "1.9.0"]]
  :profiles {
    :dev {:dependencies [[mount "0.1.12"]
                         [org.clojure/tools.namespace "0.2.11"]]}
    :repl {:source-paths ["dev"]
           :repl-options {:init-ns dev} }
    :uberjar {:aot :all
              :omit-source true
              :uberjar-name "alexa-spec.jar"}}) 
  
