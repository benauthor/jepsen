(ns jepsen.mongodb.core-test
  (:require [clojure.test :refer :all]
            [clojure.pprint :refer :all]
            [clojure.java.io :as io]
            [jepsen.mongodb [core :as m]
                            [document-cas :as dc]
                            [transfer :as t]]
            [jepsen [core      :as jepsen]
                    [util      :as util]
                    [checker   :as checker]
                    [model     :as model]
                    [tests     :as tests]
                    [generator :as gen]
                    [nemesis   :as nemesis]
                    [store     :as store]
                    [report    :as report]]))

(defn run!
  [test]
  (let [test (jepsen/run! test)]
    (is (:valid? (:results test)))))

(let [version "3.3.1"
      tarball (str "https://fastdl.mongodb.org/linux/"
                   "mongodb-linux-x86_64-debian71-" version ".tgz")
      opts {:tarball tarball}]
  (deftest document-cas-majority-test        (run! (dc/majority-test opts))))

;(deftest document-cas-no-read-majority-test (run! (dc/no-read-majority-test)))
;(deftest transfer-basic-read-test           (run! (t/basic-read-test)))
;(deftest transfer-partial-read-test         (run! (t/partial-read-test)))
;(deftest transfer-diff-account-test         (run! (t/diff-account-test)))
