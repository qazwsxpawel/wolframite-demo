(ns modern
  (:require
   [clojure.pprint :refer [pprint]]
   [clojuratica.core :as wl]
   [clojure.repl :as repl]
   [clojuratica.tools.graphics :as graphics]
   [clojuratica.base.parse :as parse :refer [custom-parse]]
   [clojuratica.lib.helpers :as h]))

;; Eval as data (note the the single quote)

(wl/eval '(Map (Function [x] (+ x 1)) [1 2 3]))

;; Load All WL Symbols

(wl/load-all-symbols (.name *ns*))

(Dot [1 2 3] [4 5 6])

(repl/doc GeoBackground)

(repl/find-doc "molecule")

(pprint (repl/apropos #"(?i)geo"))

(h/help! 'Axes)

(h/help! '(Take
            (Sort
             (Map
              (Function [gene]
                        [(GenomeData gene "SequenceLength") gene])
              (GenomeData)))
            n)
          :return-links true)

(Information 'GenomeData)

(wl/wl '((WolframLanguageData "GenomeData") "Association"))
