(ns alexa.spec-utils-test
  (:require [alexa.spec-utils :refer :all]
    [clojure.test :as t :refer [is are deftest testing use-fixtures]]))

(deftest parse-number-test
  (testing "number type or nil returned"
    (is (number? (parse-number "123")))
    (is (number? (parse-number "12.3")))
    (is (number? (parse-number "0.12")))
    (is (number? (parse-number "-0.12")))
    (is (number? (parse-number "0")))
    (is (number? (parse-number "00")))
    (is (number? (parse-number "-0")))
    (is (number? (parse-number "-1")))
    (is (number? (parse-number "99999999999999999999999999999999999999999999999999999")))
    ;; nil tests
    (is (nil? (parse-number "1/2"))) ;ratio not supported
    (is (nil? (parse-number "1^2")))
    (is (nil? (parse-number "foo")))
    (is (nil? (parse-number "--1")))
    (is (nil? (parse-number "-1a")))
    (is (nil? (parse-number " 123"))) ; no spaces
    (is (nil? (parse-number "123 "))) ; no spaces
    (is (nil? (parse-number "2E3 ")))
    (is (nil? (parse-number "2e3 ")))))

(deftest major-minor-version-string?-test
  (testing "true for correct format and false otherwise"
    (is (true? (major-minor-version-string? "1.23")))
    (is (true? (major-minor-version-string? "23.1")))
    (is (true? (major-minor-version-string? "99999.99999")))
    ;; false tests
    (is (false? (major-minor-version-string? "1.2.3")))
    (is (false? (major-minor-version-string? "1.")))
    (is (false? (major-minor-version-string? ".99")))))

(deftest img-url?-test
  (testing "image url validation"
    (is (img-url? "http://www.image-site/small-image.png"))
    (is (img-url? "http://abc.com/a.png")) 
    
    (is (not (img-url? nil)))
    (is (not (img-url? 123)))
    (is (not (img-url? [])))
    (is (not (img-url? "")))))

