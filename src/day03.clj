(ns day03
  (:require [clojure.string :as str]
            [debux.core :refer [dbg]]
            [lib :refer :all]))

(defn part-a
  [input]
  (->> input
       (re-seq #"mul\(\d+,\d+\)")
       (mapv parse-longs)
       (map #(apply * %))
       (reduce +)))

(comment
  (part-a (read-example "day03"))
  (part-a (read-input "day03")))

(defn part-b
  [input]
  (part-a (str/replace input #"(?s)don't\(\).*?(do\(\)|$)" " ")))

(comment
  (part-b (read-example "day03"))
  (part-b (read-input "day03")))
