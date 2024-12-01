(ns day01
  (:require [clojure.string :as str]
            [debux.core :refer [dbg]]
            [lib :refer :all]))

(defn part-a
  [input]
  (let [xs (parse-longs input)
        left (sort (take-nth 2 xs))
        right (sort (take-nth 2 (rest xs)))
        pairs (partition 2 (interleave left right))
        deltas (map (fn [[a b]] (abs (- a b))) pairs)]
    (sum deltas)))

(comment
  (part-a (read-example "day01"))
  (part-a (read-input "day01")))

(defn part-b
  [input]
  (let [xs (parse-longs input)
        left (sort (take-nth 2 xs))
        right (sort (take-nth 2 (rest xs)))
        mults (->> right
                   (partition-by identity)
                   (map #(vector (first %) (count %)))
                   (into {}))
        scores (map #(* % (get mults % 0)) left)]
    (sum scores))
)

(comment
  (part-b (read-example "day01"))
  (part-b (read-input "day01")))
