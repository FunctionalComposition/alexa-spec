(ns alexa.spec.output-speech
  (:require [clojure.spec.alpha :as spec]
            [clojure.string :as s]
            [alexa.spec-utils :as su]))

(def output-speech-types 
  {:plain-text "PlainText" :ssml "SSML"})

(def output-speech-play-behaviors 
  {:enqueue "ENQUEUE" :replace-all "REPLACE_ALL" :replace-enqueied "REPLACE_ENQUEUED"})

(spec/def ::sessionAttributes map?)

;; https://developer.amazon.com/docspec/custom-skillspec/request-and-response-json-reference.html#outputspeech-object
(spec/def ::type (set (vals output-speech-types)))

(spec/def ::text string?)

(spec/def ::ssml string?)

(defmulti type-requirements ::type)

(defmethod type-requirements (:plain-text output-speech-types) [_]
  (spec/keys :req [::text]))

(defmethod type-requirements (:ssml output-speech-types) [_]
  (spec/keys :req [::ssml]))

(spec/def ::type-requirements (spec/multi-spec type-requirements ::type))

(spec/def ::playBehavior (set (vals output-speech-play-behaviors)))

(spec/def ::outputSpeech
  (spec/and
    map?
    (spec/keys :req [::type]
               :opt [::playBehavior]) 
    ::type-requirements))

