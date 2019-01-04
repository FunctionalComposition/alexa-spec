(ns alexa.spec-test
  (:require [alexa.spec :as aspc]
            [alexa.spec.output-speech :as spch]
            [alexa.spec.card :as crd]
            [alexa.spec.reprompt :as rep]
            [clojure.spec.alpha :as spec]
            [clojure.test :as t :refer [is are deftest testing use-fixtures]]))

(deftest spec.version-test
  (testing "version spec validation"
    (is (spec/valid? ::aspc/version "12.3"))
    (is (spec/valid? ::aspc/version 12.3))
    (is (spec/valid? ::aspc/version "0.12"))
    (is (spec/valid? ::aspc/version "0.0"))
    (is (spec/valid? ::aspc/version 0.0))
    (is (spec/valid? ::aspc/version "999999999999999999999999999.99999999999999999999999999"))
    (is (spec/valid? ::aspc/version "000000000000000000000000000.00000000000000000000000000"))
    
    (is (not (spec/valid? ::aspc/version 1/3)))
    (is (not (spec/valid? ::aspc/version -0.0)))
    (is (not (spec/valid? ::aspc/version -0.1)))
    (is (not (spec/valid? ::aspc/version 1/2)))
    (is (not (spec/valid? ::aspc/version "1.2.3")))
    (is (not (spec/valid? ::aspc/version "123")))
    (is (not (spec/valid? ::aspc/version "-0.12")))
    (is (not (spec/valid? ::aspc/version "0")))
    (is (not (spec/valid? ::aspc/version "00")))
    (is (not (spec/valid? ::aspc/version "-0")))
    (is (not (spec/valid? ::aspc/version "-1")))
    (is (not (spec/valid? ::aspc/version "99999999999999999999999999999999999999999999999999999")))
    (is (not (spec/valid? ::aspc/version "1^2")))
    (is (not (spec/valid? ::aspc/version "foo")))
    (is (not (spec/valid? ::aspc/version "--1")))
    (is (not (spec/valid? ::aspc/version "-1a")))
    (is (not (spec/valid? ::aspc/version " 123")))
    (is (not (spec/valid? ::aspc/version "123 ")))
    (is (not (spec/valid? ::aspc/version "2E3 ")))
    (is (not (spec/valid? ::aspc/version "2e3 ")))))

(deftest sessionAttributes-test
  (testing "session attributes structure"
    (is (spec/valid? ::aspc/sessionAttributes {})) 
    (is (spec/valid? ::aspc/sessionAttributes {:key "value"})) 
    
    (is (not (spec/valid? ::aspc/sessionAttributes nil))) 
    (is (not (spec/valid? ::aspc/sessionAttributes 123))) 
    (is (not (spec/valid? ::aspc/sessionAttributes "test"))) 
    (is (not (spec/valid? ::aspc/sessionAttributes []))) 
    (is (not (spec/valid? ::aspc/sessionAttributes '())))))

(deftest response-shouldEndSession-test
  (testing "response shouldEndSession values"
    (is (spec/valid? ::aspc/response {})) 
    (is (spec/valid? ::aspc/response {::aspc/shouldEndSession true})) 
    (is (spec/valid? ::aspc/response {::aspc/shouldEndSession false})) 
    (is (not (spec/valid? ::aspc/response {::aspc/shouldEndSession nil})))
    (is (not (spec/valid? ::aspc/response {::aspc/shouldEndSession 1})))
    (is (not (spec/valid? ::aspc/response {::aspc/shouldEndSession "s"}))) 
    (is (not (spec/valid? ::aspc/response {::aspc/shouldEndSession []})))
    (is (not (spec/valid? ::aspc/response {::aspc/shouldEndSession [1]})))
    (is (not (spec/valid? ::aspc/response {::aspc/shouldEndSession {}})))
    (is (not (spec/valid? ::aspc/response {::aspc/shouldEndSession {:a 1}})))))

(deftest response-test
  (testing "response structure"
    (is (spec/valid? ::aspc/response {})) 
    (is (spec/valid? ::aspc/response
                     {::spch/outputSpeech {::spch/type "PlainText"
                                           ::spch/text "text here"}

                      ::crd/card {::crd/type "Simple"
                                  ::crd/title "card title"
                                  ::crd/content "card content"}
                      
                      ::rep/reprompt {::spch/outputSpeech {::spch/type "PlainText"
                                                           ::spch/text "text here"}}

                      ::aspc/shouldEndSession false})) 

    ;; TODO directives in response e.g. Dialog Interface Reference
    
    (is (not (spec/valid? ::aspc/sessionAttributes [])))))

