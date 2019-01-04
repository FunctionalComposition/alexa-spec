(ns alexa.spec.reprompt-test
  (:require [alexa.spec.reprompt :as rep]
            [alexa.spec.output-speech :as spch]
            [clojure.spec.alpha :as spec]
            [clojure.test :as t :refer [is are deftest testing use-fixtures]]))

(deftest reprompt-test 
  (testing "reprompt spec validation"
    (is (spec/valid? ::rep/reprompt
                     {::spch/outputSpeech 
                      {::spch/type "PlainText" 
                      ::spch/text "text here"}}))
    
    (is (not (spec/valid? ::rep/reprompt
                     {})))
    (is (not (spec/valid? ::rep/reprompt
                     nil)))))

