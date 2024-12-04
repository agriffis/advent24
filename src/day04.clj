(ns day04
  (:require
    [clojure.math.combinatorics :as combo]
    [clojure.string :as str]
    [debux.core :refer [dbg]]
    [lib :refer :all]))

(defn rxs
  [input]
  (let [w (str/index-of input \newline)]
    (for [s ["XMAS" "SAMX"]
          ndots [0 (dec w) w (inc w)]
          :let [patt (str/join (.repeat "." ndots) s)]]
      (re-pattern (str "(?s)(?=" patt ")")))))

(defn part-a
  [input]
  (->> (mapcat #(re-seq % input) (rxs input))
       count))

(comment
  (part-a (read-example "day04"))
  (part-a (read-input "day04")))

(defn rxs-b
  [input]
  (let [w (str/index-of input \newline)
        dots (.repeat "." (dec w))]
    (for [[a b c d] ["MMSS" "MSMS" "SMSM" "SSMM"]
          :let [patt (str a "\\w" b dots "A" dots c "\\w" d)]]
      (re-pattern (str "(?s)(?=" patt ")")))))

(defn part-b
  [input]
  (->> (mapcat #(re-seq % input) (rxs-b input))
       count))

(comment
  (part-b (read-example "day04"))
  (part-b (read-input "day04")))
