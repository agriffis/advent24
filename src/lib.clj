(ns lib
  (:require [clojure.java.io :as io]
            [clojure.string :as str]))

(defn read-input
  ([name] (read-input name "input"))
  ([name suffix]
   (str/trim-newline (slurp (io/resource (str name "/" suffix))))))

(defn read-example
  [name]
  (read-input name "example"))

(defn split-paragraphs
  [s]
  (str/split s #"\n\n+"))

(defn split-lines
  [s]
  (str/split-lines s))

(defn split-words
  [s]
  (re-seq #"\S+" s))

(defn parse-longs
  [s]
  (->> (re-seq #"\d+" s) (map Long/parseLong)))

(defn parse-bigints
  [s]
  (->> (re-seq #"\d+" s) (map bigint)))

(defn sum
  [xs]
  (reduce + xs))
