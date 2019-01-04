(ns alexa.spec.card-test
  (:require [alexa.spec.card :as card]
            [alexa.spec.card.image :as img]
            [clojure.spec.alpha :as spec]
            [clojure.test :as t :refer [is are deftest testing use-fixtures]]))

(deftest card-title-test 
  (testing "card title spec validation"
    (is (spec/valid? ::card/title "card title"))
    (is (not (spec/valid? ::card/title nil)))
    (is (not (spec/valid? ::card/title 123)))
    (is (not (spec/valid? ::card/title [])))))
    
(deftest card-content-test 
  (testing "card content spec validation"
    (is (spec/valid? ::card/content "card content"))
    (is (not (spec/valid? ::card/content nil)))
    (is (not (spec/valid? ::card/content 123)))
    (is (not (spec/valid? ::card/content [])))))
    
(deftest card-text-test 
  (testing "card text spec validation"
    (is (spec/valid? ::card/text "card text"))
    (is (not (spec/valid? ::card/text nil)))
    (is (not (spec/valid? ::card/text 123)))
    (is (not (spec/valid? ::card/text [])))))

(deftest card-test 
  (testing "card spec validation"
    (is (spec/valid? ::card/card {::card/type "Simple"
                                  ::card/title "card title" 
                                  ::card/content "card content"}))
    (is (spec/valid? ::card/card {::card/type "Standard"
                                  ::card/title "card title" 
                                  ::card/text "card text"}))
    (is (not (spec/valid? ::card/card {::card/type "Standard"
                                  ::card/title "card title" 
                                  ::img/image {::img/smallImageUrl "http://a/a.png" 
                                               ::img/largeImageUrl "http://a/a.png"}})))
    (is (not (spec/valid? ::card/card {::card/type "Standard"
                                  ::card/title "card title" 
                                  ::img/image {}})))
    (is (spec/valid? ::card/card {::card/type "LinkAccount"}))

    (is (not (spec/valid? ::card/card nil)))))

