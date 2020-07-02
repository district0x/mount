(defproject district0x/mount "0.1.17-SNAPSHOT"
  :description "managing Clojure and ClojureScript app state since (reset)"
  :url "https://github.com/tolitius/mount"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}

  :source-paths ["src"]

  :dependencies [[fullcontact/full.async "1.1.0"]] ;; for visual clarity

  :tach {:test-runner-ns 'mount.test-self-host
         :source-paths ["test/core"]}

  :profiles {:dev {:source-paths ["dev" "dev/clj" "test/clj"]
                   :dependencies [[org.clojure/clojure "1.8.0"]
                                  [org.clojure/clojurescript "1.10.773"]; :classifier "aot"]
                                  [datascript "0.18.13" ]
                                  [compojure "1.4.0"]
                                  [ring/ring-jetty-adapter "1.1.0"]
                                  [cheshire "5.5.0"]
                                  [hiccups "0.3.0"]
                                  [com.andrewmcveigh/cljs-time "0.3.14"]
                                  [ch.qos.logback/logback-classic "1.1.3"]
                                  [org.clojure/tools.logging "0.3.1"]
                                  [robert/hooke "1.3.0"]
                                  [org.clojure/tools.namespace "0.2.11"]
                                  [org.clojure/tools.nrepl "0.2.11"]
                                  [com.datomic/datomic-free "0.9.5327" :exclusions [joda-time com.google.guava/guava]]]

                   :plugins [[lein-cljsbuild "1.1.8"]
                             [lein-doo "0.1.6"]
                             [lein-figwheel "0.5.0-2"]
                             [test2junit "1.1.3"]
                             [lein-tach "1.0.0"]]

                   :test2junit-output-dir ~(or (System/getenv "CIRCLE_TEST_REPORTS") "target/test2junit")

                   :clean-targets ^{:protect false} [:target-path
                                                     [:cljsbuild :builds :dev :compiler :output-dir]
                                                     [:cljsbuild :builds :prod :compiler :output-to]]

                   :deploy-repositories [["snapshots" {:url "https://clojars.org/repo"
                                                       :username :env/clojars_username
                                                       :password :env/clojars_password
                                                       :sign-releases false}]
                                         ["releases"  {:url "https://clojars.org/repo"
                                                       :username :env/clojars_username
                                                       :password :env/clojars_password
                                                       :sign-releases false}]]

                   :release-tasks [["vcs" "assert-committed"]
                                   ["change" "version" "leiningen.release/bump-version" "release"]
                                   ["deploy"]]

                   :cljsbuild {
                               :builds {:dev
                                        {:source-paths ["src" "dev/cljs"]
                                         :figwheel true

                                         :compiler {:main app.example
                                                    :asset-path "js/compiled/out"
                                                    :output-to "dev/resources/public/js/compiled/mount.js"
                                                    :output-dir "dev/resources/public/js/compiled/out"
                                                    :optimizations :none
                                                    :source-map true
                                                    :source-map-timestamp true}}
                                        :test
                                        {:source-paths ["src" "dev/cljs" "test"]
                                         :compiler {:main mount.test
                                                    ;; :asset-path "js/compiled/out"
                                                    :output-to "dev/resources/public/js/compiled/mount.js"
                                                    :output-dir "dev/resources/public/js/compiled/test"
                                                    :target :nodejs
                                                    :optimizations :none
                                                    :source-map true
                                                    :source-map-timestamp true}}
                                        :async-test
                                        {:source-paths ["src" "test/cljs"]
                                         :compiler {:main async.app
                                                    ;; :asset-path "js/compiled/out"
                                                    :output-to "dev/resources/public/js/compiled/async-test.js"
                                                    :output-dir "dev/resources/public/js/compiled/async-test"
                                                    :optimizations :none
                                                    :source-map true
                                                    :target :nodejs
                                                    :source-map-timestamp true}}
                                        :prod
                                        {:source-paths ["src" "dev/cljs"]
                                         :compiler {:output-to "dev/resources/public/js/compiled/mount.js"
                                                    :optimizations :advanced
                                                    :pretty-print false}}}}}

             :test {:source-paths ["test/core" "test/clj" "test/cljs"]}})
