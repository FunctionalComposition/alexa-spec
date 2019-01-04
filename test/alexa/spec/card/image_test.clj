(ns alexa.spec.card.image-test
  (:require [alexa.spec.card.image :as img]
            [clojure.spec.alpha :as spec]
            [clojure.test :as t :refer [is are deftest testing use-fixtures]]))

(deftest small-large-image-test
  (testing "card image spec validation"
    (is (spec/valid? ::img/smallImageUrl "http://www.image-site/small-image.png"))
    (is (spec/valid? ::img/smallImageUrl "https://www.image-site/small-image.jpg"))
    (is (spec/valid? ::img/largeImageUrl "http://www.image-site/large-image.png"))
    (is (spec/valid? ::img/largeImageUrl "https://www.image-site/large-image.jpg"))
    (is (spec/valid? ::img/largeImageUrl "http://a.com/a.png"))
    
    (is (not (spec/valid? ::img/largeImageUrl nil)))
    (is (not (spec/valid? ::img/largeImageUrl 123)))
    (is (not (spec/valid? ::img/largeImageUrl [])))
    (is (not (spec/valid? ::img/largeImageUrl ""))))) 

(deftest image-test 
  (testing "card image spec validation"
    (is (spec/valid? ::img/image 
                     {::img/smallImageUrl "https://www.abc.com/small-image.png" 
                      ::img/largeImageUrl "https://www.abc.com/large-image.png"}))
    (is (not (spec/valid? ::img/image 
                     {::img/smallImageUrl "https://www.abc.com/same-image.png" 
                      ::img/largeImageUrl "https://www.abc.com/same-image.png"})))
    (is (not (spec/valid? ::img/image 
                         {::img/smallImageUrl "http://not-img-url.com/abc"
                          ::img/largeImageUrl "https://www.abc.com/large-image.png"})))
    (is (not (spec/valid? ::img/image nil)))
    (is (not (spec/valid? ::img/image 123)))
    (is (not (spec/valid? ::img/image [])))
    (is (not (spec/valid? ::img/image {})))
    (is (not (spec/valid? ::img/image {::img/smallImageUrl ""})))
    (is (not (spec/valid? ::img/image {::img/smallImageUrl "http://aaa.com/sml.png"})))
    (is (not (spec/valid? ::img/image 
                          {::img/smallImageUrl "http://aaa.com/sml.png"
                           ::img/largeImageUrl ""})))
    (is (not (spec/valid? ::img/image ::img/smallImageUrl)))))

