(ns alexa.spec.card.image
  (:require [alexa.spec-utils :as su]
            [clojure.spec.alpha :as spec]))

(spec/def ::smallImageUrl
 (spec/and string?
           su/img-url?))

(spec/def ::largeImageUrl 
  (spec/and string?
            su/img-url?))

(spec/def ::image
  (spec/and 
    (spec/keys :req [::smallImageUrl ::largeImageUrl])
    su/distinct-map-vals?))

