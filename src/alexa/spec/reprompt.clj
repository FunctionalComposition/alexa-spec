(ns alexa.spec.reprompt
  (:require [clojure.spec.alpha :as spec]
            [alexa.spec.output-speech :as spch]
            [clojure.string :as s]
            [alexa.spec-utils :as su]))

(spec/def ::reprompt
  (spec/keys :req [::spch/outputSpeech]))

