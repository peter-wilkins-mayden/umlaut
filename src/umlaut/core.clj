(ns umlaut.core
  (:require [clojure.java.io :as io]
            [clojure.string :as str]
            [umlaut.parser :refer [parse]]
            [umlaut.models :as model]
            [umlaut.utils :as utils]
            [clojure.spec :as s]
            [clojure.spec.test :as stest])
  (:gen-class))
(use '[clojure.pprint :only [pprint]])

(defn- read-parse [path]
  "Read all the umlaut files from a folder and parse its content"
  (->> path
      (list)
      (flatten)
      (reduce (fn [acc filename]
                (let [parsed (parse (slurp filename))]
                  (utils/map-extend acc {:nodes (parsed :nodes)
                                          :diagrams (parsed :diagrams)
                                          :annotations (concat (acc :annotations) (parsed :annotations))})))
              {})))

(defn- read-folder [path]
  "Validate all the umlaut code parsed from a folder"
  read-parse path
  (let [parsed (read-parse path)]
    (if (not (s/valid? ::model/namespaces parsed))
      (throw (Exception. (s/explain ::model/namespaces parsed)))
      parsed)))

(defn main
  "Parses, validates, and transform the umlaut files from a folder"
  [path]
  (read-folder path))

(s/fdef main
        :ret ::model/namespaces)

(stest/instrument `main)


; (pprint (read-folder ["test/fixtures/person/person.umlaut"  "test/fixtures/person/profession.umlaut"]))
