(ns alexa.spec.card
  (:require [clojure.spec.alpha :as spec]
            [alexa.spec.card.image :as img]
            [clojure.string :as s]
            [alexa.spec-utils :as su]))

(def card-types
  {:Simple "Simple" :Standard "Standard" :LinkAccount "LinkAccount" :AskForPermissionsConsent "AskForPermissionsConsent"})

(spec/def ::title string?)

(spec/def ::content string?)

(spec/def ::text string?)

(spec/def ::type (set (vals card-types)))

(defmulti card-type ::type)

(defmethod card-type (:Simple card-types) [_]
  (spec/keys :req [::title ::content])
  (spec/and
   #(not (contains? % ::text))
   #(not (contains? % ::img/image))))

(defmethod card-type (:Standard card-types) [_]
  (spec/keys :req [::title ::text] :opt [::img/image])
  (spec/and
   #(not (contains? % ::content))))

(defmethod card-type (:LinkAccount card-types) [_]
  (spec/and
   #(not (contains? % ::title))
   #(not (contains? % ::content))
   #(not (contains? % ::text))
   #(not (contains? % ::img/image))))

(defmethod card-type (:AskForPermissionsConsent card-types) [_]
  nil?) ;; TODO implement

(spec/def ::card-type (spec/multi-spec card-type ::type))

(spec/def ::card
  (spec/and map?
            (spec/keys :req [::type]
                       :opt [::title ::content ::text])
            ::card-type))

