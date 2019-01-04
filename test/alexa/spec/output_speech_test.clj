(ns alexa.spec.output-speech-test
  (:require [alexa.spec.output-speech :as outputSpeech]
            [clojure.spec.alpha :as spec]
            [clojure.test :as t :refer [is are deftest testing use-fixtures]]))

(deftest card-output-speech-test 
  (testing "card outputSpeech spec validation"
    (is (spec/valid? ::outputSpeech/outputSpeech
                     {::outputSpeech/type "PlainText" 
                      ::outputSpeech/text "text here"}))
    (is (spec/valid? ::outputSpeech/outputSpeech 
                     {::outputSpeech/type "PlainText" 
                      ::outputSpeech/text "text here"
                      ::outputSpeech/playBehavior "ENQUEUE"}))
    (is (spec/valid? ::outputSpeech/outputSpeech 
                     {::outputSpeech/type "SSML"
                      ::outputSpeech/ssml "ssml here"}))
    (is (spec/valid? ::outputSpeech/outputSpeech 
                     {::outputSpeech/type "SSML"
                      ::outputSpeech/ssml "ssml here"
                      ::outputSpeech/playBehavior "ENQUEUE"}))
    (is (not (spec/valid? ::outputSpeech/outputSpeech
                          {::outputSpeech/type "PlainText"
                           ::outputSpeech/text "text here"
                           ::outputSpeech/playBehavior "BADVAL"})))
    (is (not (spec/valid? ::outputSpeech/outputSpeech 
                          {::outputSpeech/type "PlainText"})))
    (is (not (spec/valid? ::outputSpeech/outputSpeech 
                          {::outputSpeech/type "PlainText"
                           :undefinedKey "undefined key val"})))
    (is (not (spec/valid? ::outputSpeech/outputSpeech 
                          {::outputSpeech/type "PlainText" 
                           ::outputSpeech/ssml "ssml here"})))
    (is (not (spec/valid? ::outputSpeech/outputSpeech 
                          {::outputSpeech/type "SSML" 
                           ::outputSpeech/text "text here"})))
    (is (not (spec/valid? ::outputSpeech/outputSpeech 
                          {::outputSpeech/type "SSML" 
                           ::outputSpeech/text "text here" 
                           ::outputSpeech/playBehavior "ENQUEUE"})))))

