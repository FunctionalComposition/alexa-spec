(ns alexa.spec-utils)

(defn parse-number
  "Reads a number from a string. Returns nil if not a number."
  [s]
  (if (re-find #"^-?\d+\.?\d*$" s)
    (read-string s)))

(defn major-minor-version-string?
  "Returns true if the supplied value is in the format of a major.minor version string
  and false otherwise e.g. \"1.0\" is valid \"2.\" is invalid \"0.999\" is valid etc... "
  [s]
  (if (re-find #"^\d+\.\d+$" (str s))
    true
    false))

(defn in? 
  "true if coll contains elm"
  [coll elm]
  (some #(= elm %) coll))

(def not-in? (complement in?))

(defn img-url?
  "crude validation of image url" 
  [url]
  (if (and (string? url) (re-matches #"http(s)?:\/\/.+\..+\/.+(\.jpg|\.png|\.gif)" url))
    true
    false))

(defn distinct-map-vals?
  "returns true if all vals in a map are unique i.e. no duplicates"
  [m]
  (= (vals m) (distinct (vals m))))

