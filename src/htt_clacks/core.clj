(ns htt-clacks.core
  (:require [htt-clacks.clacks-alphabet :refer [alphabet]]
            [clojure.set]))

(def alphabet-inverted (clojure.set/map-invert alphabet))

;; Convert a character to a binary code

(defn character->clack [char alphabet]
  (let [character (str char)]
    (get alphabet character)))

(character->clack "a" alphabet)

;; Convert a string to a binary code

(defn message->clacks [message alphabet]
  (map #(character->clack % alphabet) message))

(message->clacks "cab" alphabet)
;; => ([0 1 1 0 0 0 1 1] [0 1 1 0 0 0 0 1] [0 1 1 0 0 0 1 0])

(message->clacks "cab cab" alphabet)
;; => ([0 1 1 0 0 0 1 1] [0 1 1 0 0 0 0 1] [0 1 1 0 0 0 1 0] [0 0 0 0 0 0 0 0] [0 1 1 0 0 0 1 1] [0 1 1 0 0 0 0 1] [0 1 1 0 0 0 1 0])

;; Create a charater from a clack code

#_(defn clack->character [clack alphabet]
  (get (clojure.set/map-invert alphabet) clack))

(defn clack->character [clack alphabet]
  (get alphabet-inverted clack))

;; Create a clacks code back to a message

(defn clacks->message [clacks alphabet]
  (reduce str (map #(clack->character % alphabet) clacks)))

;; test data
(clacks->message '([0 1 1 0 0 0 1 1] [0 1 1 0 0 0 0 1] [0 1 1 0 0 0 1 0]) alphabet)

(clacks->message
 '([0 1 1 0 0 0 1 1] [0 1 1 0 0 0 0 1] [0 1 1 0 0 0 1 0] [0 0 0 0 0 0 0 0] [0 1 1 0 0 0 1 1] [0 1 1 0 0 0 0 1] [0 1 1 0 0 0 1 0]) alphabet)

;; (map str ["c" "a" "b"])
;; (reduce str ["c" "a" "b"])
