(ns alexa.spec
  (:require [clojure.spec.alpha :as spec]
            [alexa.spec.reprompt :as rep]
            [clojure.string :as s]
            [alexa.spec.card :as crd]
            [alexa.spec.output-speech :as spch]
            [alexa.spec-utils :as su]))

(spec/def ::version su/major-minor-version-string?)

(spec/def ::sessionAttributes map?)

(spec/def ::shouldEndSession boolean?)

(spec/def ::response
  (spec/keys :opt [::spch/outputSpeech ::crd/card ::rep/reprompt ::shouldEndSession ::directives]))

(spec/def ::response-body-params
  (spec/keys
    :req [::version ::response]
    :opt [::sessionAttributes]))

