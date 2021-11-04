(ns demo
  (:require
   [clojuratica.core :as wl :refer [WL math-intern math-evaluate kernel-link wl->clj clj->wl]]
   [clojuratica.tools.graphics :as graphics :refer [make-app! make-math-canvas! show!]]))


;; * Clojure a 1min intro
;; ** Interactive developement
;; *** Evaluating forms from a text editor connected to a REPL

(+ 1 1)

;; ** Syntax a.k.a. data structures
;; *** Interesting scalar datatypes

;; - symbols: a b c d

;; unqoted
;; x

;; (def x "hello")

;; quoted

'a

;; - keywords

:hello

;; namespaced

:project.media/image


;; *** Interesting compund datatypes

;; vector

[1 3 :a :b 'foo 'bar]

;; hash map

{:a 23
 :b 73}

;; * Wolframite (nee Clojuratica) -- name is a WIP

;; Init

(WL (Dot [1 2 3] [4 5 6]))

(WL '"{1 , 2, 3} . {4, 5, 6}")

(WL (Plus 23 '"{1 , 2, 3} . {4, 5, 6}"))

(math-intern math-evaluate Plus)

#_{:clj-kondo/ignore true}
(Plus [1 2] [3 4])

(def greetings
  (WL
   (Function [x] (StringJoin "Hello, " x "! This is a Mathematica function's output."))))

(greetings "Stephen")

;; threading fun

(-> "Ocean"
    Entity
    (GeoNearest Here)
    WL)

;; - Wolfram Alpha

(WL (WolframAlpha "number of moons of Saturn" "Result"))

;; - more examples

;; - WL syntactic sugar
(WL @(a b c d))

;; - more interesting examples...

(WL (TextStructure "The cat sat on the mat."))

(WL (TextStructure "You can do so much with the Wolfram Language." "ConstituentGraphs"))

;; - Bidirectional translation

(wl->clj "GridGraph[{5, 5}]" math-evaluate)

(clj->wl '(GridGraph [5 5]) {:kernel-link kernel-link
                             :output-fn str})

;; ** Graphics

;; *** Init math canvas & app

(def canvas (make-math-canvas! kernel-link))
(def app (make-app! canvas))

;; *** Draw Something!

(show! canvas (clj->wl '(GridGraph [5 5]) {:output-fn str}))
(show! canvas (clj->wl '(ChemicalData "Ethanol" "StructureDiagram") {:output-fn str}))

;; *** Make it easier (Dev Helper: closing over the canvas)

(defn quick-show [clj-form]
  (show! canvas (clj->wl clj-form {:output-fn str})))

;; *** Some Simple Graphiscs Examples

(quick-show '(ChemicalData "Ethanol" "StructureDiagram"))
(quick-show '(GridGraph [5 5]))
(quick-show '(GeoImage (Entity "City" ["NewYork" "NewYork" "UnitedStates"])))

(comment ;; WIP

  ;; TODO: understand scopes
  (math-intern math-evaluate :scopes)

  ;; TODO Fix this one
  ;; prnc: need to understand this scoping construct better, seemed to work :/
  #_{:clj-kondo/ignore true}
  (Let [t (-> ["Text" "AliceInWonderland"]
              ExampleData
              ContentObject
              Snippet)]
       (WordFrequency ~t (TextWords ~t)))

  :end-comment)
