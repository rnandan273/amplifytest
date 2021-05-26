(ns cljapp.views
  (:require
   [re-frame.core :as re-frame]
   [cljapp.events :as events]
   [reagent.core :as r]
   ["semantic-ui-react" :as ui]
   [cljapp.subs :as subs]))

(defonce state (r/atom 0))

(defn account2 []
  [:div [:h2 "Welcome to Semantic UI"]
   [:> ui/Label {:color :blue}
    [:> ui/Label.Detail "Clicks: " @state]]
   [:> ui/Button
    {:basic    true
     :color    "red"
     :on-click (fn [] (swap! state inc))}
    "count up"]])


 
(defn navbar []
[:div.columns.is-centered.is-vcentered.is-mobile.p-4
   [:div.column [:a {:href "/#/" :style {:font-size 18 :font-weight "bold"}} "GIGION"]]
   [:div.column.is-6 [:a {:href "/#/" :style {:font-size 18 :font-weight "bold"}} "HOME PAGE"]]
   [:div.column.is-1 [:a {:href "/#/login"} "Sign In"]]])

(defn item [title link description]
  [:div.box.p-5
  [:div.columns.is-centered.is-vcentered
   [:div.column.is-5
    [:div.title.is-5 {:style {:text-align "center"}}
     (str title)]]
 
    [:div.column
     [:div.title.is-6 {:style {:text-align "left"}}
       (str "Please ")[:a {:href link :style {:color "red" :margin "0% 0.5% 0% 0.5%"}} "click here"] (str description)]]]])
  
(defn home-page []
  (fn []
    [:div.section
     [:div.container.is-fluid.mt-5.pt-5 
      [:div.box [:div.columns.is-centered.is-vcentered
                 [:div.column
                  [:div.title.is-8.has-text-info {:style {:text-align "center"}}
                   (str "Welcome to the Worlds first micro-consulting platform")]]]]

      [item (str "Your expertise can help someone, somewhere in the world and we can help you to monetize your expertise")
       "/#/signup"
       "to sign up as a provider"]

      [item (str "Are you looking at a Job at a certain company?")
       "/#/signup"
       "and we can connect you with an employee at that company that can help you"]

      [item (str "Are you planning to pursue higher education at a certain college?")
       "/#/signup"
       "and we can connect you with an current or former student or faculty at that college who can answer all your questions"]

      [item (str "Are you a Recruiter?")
       "/#/signup"
       "and we can connect you with employees at any company that you want to target"]

      [item (str "Are you a Private Tutor?")
       "/#/signup"
       "and we can help streamline and monetize your outside the classroom communications with all your students"]

      [item (str "Are you a Doctor??")
       "/#/signup"
       "and we can help streamline and monetize your outside the clinic communications with all your patients"]]]
    ))

(defn login-page []
  (fn []
    [:div.section
     [:div.container.is-fluid.mt-5.pt-5
      [:div.columns.is-centered.mt-5
       [:div.column]
       [:div.column.is-4.m-1
        [:label.box.label.is-medium.has-text-centered "Sign In"]]
       [:div.column]]

      [:div.columns.is-entered
       [:div.column]
       [:div.column.is-4.m-1
        [:form.box
         [:div.field
          [:label.label "Email"]
          [:input.input {:type "email" :placeholder "e.g bobsmith@gmail.com"}]]
         [:div.field
          [:label.label "Password"]
          [:input.input {:type "password" :placeholder "***********" :required true}]]
         [:div.field
          [:label.label.is-info "Forgot Password?"]]
         [:div.columns.is-centered.is-mobile
          [:div.column.is-offset-1
           [:button.button
            [:a {:href "/#/"} "Back"]]]
          [:div.column.is-offset-1
           [:button.button
            [:a {:href "/#/user-account"} "Sign In"]]]]]]
       [:div.column]]]]))


(defn signup-page []
  (fn []
    [:div.section
     [:div.container.is-fluid.mt-5.pt-5
      
      [:div.columns.is-centered
       [:div.column.is-5
        [:label.box.label.is-medium.has-text-centered "Sign Up"]]]
      [:div.columns.is-centered
       [:div.column.is-5
        [:form.box
         [:div.field
          [:label.label "First Name"]
          [:input.input {:type "text" :placeholder "First Name"}]]

         [:div.field
          [:label.label "Last Name"]
          [:input.input {:type "text" :placeholder "Last Name"}]]
         [:div.field
          [:label.label "Email"]
          [:input.input {:type "text" :placeholder "Email"}]]
         [:div.field
          [:label.label "Select Password"]
          [:input.input {:type "password" :placeholder "Select Password"}]]
         [:div.field
          [:label.label "Repeat Password"]
          [:input.input {:type "password" :placeholder "Repeat Password"}]]
         [:div.field
          [:label.checkbox
           [:input.mr-2 {:type "checkbox"}]
           "I have read and agree with your"
           [:a {:href "#"} "terms of service"]
           [:a {:href "#"} "privacy policy"]]]
         [:div.columns.is-centered.is-mobile
          [:div.column.is-offset-1
           [:button.button
            [:a {:href "/#/"} "Back"]]]
          [:div.column.is-offset-1
           [:button.button
            [:a {:href "/#/user-account-services"} "Sign Up"]]]]]]]]]))

(defn service-log-page []
  (fn []
    [:section.hero
     [:div.hero-body
      [:div.container
       [:div.columns.is-mobile {:style {:margin-bottom "50%"}}

        [:div.column
         [:label.label [:a "Service Log"]]
         [:h4 "Chat"] [:div "Requested"]
         [:div "Scheduled"]]]]]]))

(defn card-grid [items]
  [:div.columns.is-centered {:style {:margin "0% 0% -1% 0%"}}
   (for [item items]
      ^{:key (str "at11 -" (rand 10000000))}
     (case (:margin item)
       0 [:div.column (:label item)]
       [(keyword (str "div.column." "is-" (:margin item))) (:label item)]))])

(defn requestor-chat [status provider]
  (fn []
    (case status
      "requested" [:div.box.mt-1  {:style {:background-color "#F6EDC2"}}
                   (for [item [[{:margin 3 :label "Chat"}
                                {:margin 0 :label (str "Provider: " provider)}]
                               [{:margin 3 :label "Requested"} {:margin 0 :label ""}]
                               [{:margin 3 :label "Deadline"} {:margin 0 :label ""}]
                               [{:margin 3 :label [:a {:href "/"} "Accept"]}
                                {:margin 3 :label [:a {:href "/"} "Decline"]}]]]
                     ^{:key (str "at11 -" (rand 10000000))}
                     [card-grid item])]
      "declined" [:div.box.mt-1  {:style {:background-color "#F6EDC2"}}
                  (for [item [[{:margin 3 :label "Chat"} 
                               {:margin 0 :label (str "Provider: " provider)}]
                              [{:margin 3 :label "Requested"} {:margin 0 :label ""}]
                              [{:margin 3 :label "Deadline"} {:margin 0 :label ""}]
                              [{:margin 3 :label "Cancelled"} {:margin 0 :label ""}]]]
                    ^{:key (str "at11 -" (rand 10000000))}
                     [card-grid item])]
      
      "accepted" [:div.box.mt-1  {:style {:background-color "#F6EDC2"}}
                  (for [item [[{:margin 3 :label "Chat"}
                               {:margin 0 :label (str "Provider: " provider)}
                               {:margin 3 :label [:a {:href "/"} "Cancel"]}]
                              [{:margin 3 :label "Requested"} {:margin 0 :label ""}]
                              [{:margin 3 :label "Deadline"} {:margin 0 :label ""}]]]
                    ^{:key (str "at11 -" (rand 10000000))}
                     [card-grid item])]
      "failed" [:div.box.mt-1  {:style {:background-color "#F6EDC2"}}
                
                (for [item [[{:margin 3 :label "Chat"}
                             {:margin 0 :label (str "Provider: " provider)}]
                            [{:margin 3 :label "Requested"} {:margin 0 :label ""}]
                            [{:margin 3 :label "Deadline"} {:margin 0 :label ""}]
                            [{:margin 3 :label "Expired"} {:margin 0 :label ""}]]]
                  ^{:key (str "at11 -" (rand 10000000))}
                  [card-grid item])]
      
      "completed" [:div.box.mt-1  {:style {:background-color "#F6EDC2"}}
                   (for [item [[{:margin 3 :label "Chat"}
                                {:margin 0 :label (str "Provider: " provider)}
                                {:margin 3 :label [:a {:href "/"} "Transcript"]}]
                               [{:margin 3 :label "Requested"} {:margin 0 :label ""}]
                               [{:margin 3 :label "Requested"} {:margin 0 :label ""}]
                               [{:margin 3 :label "Completed"} {:margin 0 :label ""}]]]
                     ^{:key (str "at11 -" (rand 10000000))}
                     [card-grid item])])))

(defn provider-chat [status requestor]
  (fn []
    (case status
      "requested" [:div.box.mt-1  {:style {:background-color "#F6EDC2"}}
                   (for [item [[{:margin 3 :label "Chat"}
                                {:margin 0 :label (str "Requestor: " requestor)}]
                               [{:margin 3 :label "Requested"} {:margin 0 :label ""}]
                               [{:margin 3 :label "Deadline"} {:margin 0 :label ""}]
                               [{:margin 3 :label [:a {:href "/"} "Accept"]}
                                {:margin 3 :label [:a {:href "/"} "Decline"]}]]]
                     ^{:key (str "at11 -" (rand 10000000))}
                     [card-grid item])]
      "declined" [:div.box.mt-1  {:style {:background-color "#F6EDC2"}}
                  (for [item [[{:margin 3 :label "Chat"} 
                               {:margin 0 :label (str "Requestor: " requestor)}]
                              [{:margin 3 :label "Requested"} {:margin 0 :label ""}]
                              [{:margin 3 :label "Deadline"} {:margin 0 :label ""}]
                              [{:margin 3 :label "Cancelled"} {:margin 0 :label ""}]]]
                    ^{:key (str "at11 -" (rand 10000000))}
                     [card-grid item])]
      
      "accepted" [:div.box.mt-1  {:style {:background-color "#F6EDC2"}}
                  (for [item [[{:margin 3 :label "Chat"}
                               {:margin 0 :label (str "Requestor: " requestor)}
                               {:margin 3 :label [:a {:href "/"} "Cancel"]}]
                              [{:margin 3 :label "Requested"} {:margin 0 :label ""}]
                              [{:margin 3 :label "Deadline"} {:margin 0 :label ""}]]]
                    ^{:key (str "at11 -" (rand 10000000))}
                     [card-grid item])]
      "failed" [:div.box.mt-1  {:style {:background-color "#F6EDC2"}}
                
                (for [item [[{:margin 3 :label "Chat"}
                             {:margin 0 :label (str "Requestor: " requestor)}]
                            [{:margin 3 :label "Requested"} {:margin 0 :label ""}]
                            [{:margin 3 :label "Deadline"} {:margin 0 :label ""}]
                            [{:margin 3 :label "Expired"} {:margin 0 :label ""}]]]
                  ^{:key (str "at11 -" (rand 10000000))}
                  [card-grid item])]
      
      "completed" [:div.box.mt-1  {:style {:background-color "#F6EDC2"}}
                   (for [item [[{:margin 3 :label "Chat"}
                                {:margin 0 :label (str "Requestor: " requestor)}
                                {:margin 3 :label [:a {:href "/"} "Transcript"]}]
                               [{:margin 3 :label "Requested"} {:margin 0 :label ""}]
                               [{:margin 3 :label "Requested"} {:margin 0 :label ""}]
                               [{:margin 3 :label "Completed"} {:margin 0 :label ""}]]]
                     ^{:key (str "at11 -" (rand 10000000))}
                     [card-grid item])])))

(defn requestor-message [status provider]
  (fn []
    (case status
      "requested" [:div.box.mt-1  {:style {:background-color "#F6EDC2"}}
                   (for [item [[{:margin 3 :label "Message"}
                                {:margin 0 :label (str "Provider: " provider)}]
                               [{:margin 3 :label "Requested"} {:margin 0 :label ""}]
                               [{:margin 3 :label "Deadline"} {:margin 0 :label ""}]
                               [{:margin 3 :label [:a {:href "/"} "Accept"]}
                                {:margin 3 :label [:a {:href "/"} "Decline"]}]]]
                     ^{:key (str "at11 -" (rand 10000000))}
                     [card-grid item])]
      "declined" [:div.box.mt-1  {:style {:background-color "#F6EDC2"}}
                  (for [item [[{:margin 3 :label "Message"}
                               {:margin 0 :label (str "Provider: " provider)}]
                              [{:margin 3 :label "Requested"} {:margin 0 :label ""}]
                              [{:margin 3 :label "Deadline"} {:margin 0 :label ""}]
                              [{:margin 3 :label "Cancelled"} {:margin 0 :label ""}]]]
                    ^{:key (str "at11 -" (rand 10000000))}
                    [card-grid item])]

      "accepted" [:div.box.mt-1  {:style {:background-color "#F6EDC2"}}
                  (for [item [[{:margin 3 :label "Message"}
                               {:margin 0 :label (str "Provider: " provider)}
                               {:margin 3 :label [:a {:href "/"} "Cancel"]}]
                              [{:margin 3 :label "Requested"} {:margin 0 :label ""}]
                              [{:margin 3 :label "Deadline"} {:margin 0 :label ""}]]]
                    ^{:key (str "at11 -" (rand 10000000))}
                    [card-grid item])]
      "failed" [:div.box.mt-1  {:style {:background-color "#F6EDC2"}}

                (for [item [[{:margin 3 :label "Message"}
                             {:margin 0 :label (str "Provider: " provider)}]
                            [{:margin 3 :label "Requested"} {:margin 0 :label ""}]
                            [{:margin 3 :label "Deadline"} {:margin 0 :label ""}]
                            [{:margin 3 :label "Expired"} {:margin 0 :label ""}]]]
                  ^{:key (str "at11 -" (rand 10000000))}
                  [card-grid item])]

      "completed" [:div.box.mt-1  {:style {:background-color "#F6EDC2"}}
                   (for [item [[{:margin 3 :label "Message"}
                                {:margin 0 :label (str "Provider: " provider)}
                                {:margin 3 :label [:a {:href "/"} "Transcript"]}]
                               [{:margin 3 :label "Requested"} {:margin 0 :label ""}]
                               [{:margin 3 :label "Requested"} {:margin 0 :label ""}]
                               [{:margin 3 :label "Completed"} {:margin 0 :label ""}]]]
                     ^{:key (str "at11 -" (rand 10000000))}
                     [card-grid item])])))

(defn provider-message [status requestor]
  (fn []
    (case status
      "requested" [:div.box.mt-1  {:style {:background-color "#F6EDC2"}}
                   (for [item [[{:margin 3 :label "Message"}
                                {:margin 0 :label (str "Requestor: " requestor)}]
                               [{:margin 3 :label "Requested"} {:margin 0 :label ""}]
                               [{:margin 3 :label "Deadline"} {:margin 0 :label ""}]
                               [{:margin 3 :label [:a {:href "/"} "Accept"]}
                                {:margin 3 :label [:a {:href "/"} "Decline"]}]]]
                     ^{:key (str "at11 -" (rand 10000000))}
                     [card-grid item])]
      "declined" [:div.box.mt-1  {:style {:background-color "#F6EDC2"}}
                  (for [item [[{:margin 3 :label "Message"} 
                               {:margin 0 :label (str "Requestor: " requestor)}]
                              [{:margin 3 :label "Requested"} {:margin 0 :label ""}]
                              [{:margin 3 :label "Deadline"} {:margin 0 :label ""}]
                              [{:margin 3 :label "Cancelled"} {:margin 0 :label ""}]]]
                    ^{:key (str "at11 -" (rand 10000000))}
                     [card-grid item])]
      
      "accepted" [:div.box.mt-1  {:style {:background-color "#F6EDC2"}}
                  (for [item [[{:margin 3 :label "Message"}
                               {:margin 0 :label (str "Requestor: " requestor)}
                               {:margin 3 :label [:a {:href "/"} "Cancel"]}]
                              [{:margin 3 :label "Requested"} {:margin 0 :label ""}]
                              [{:margin 3 :label "Deadline"} {:margin 0 :label ""}]]]
                    ^{:key (str "at11 -" (rand 10000000))}
                     [card-grid item])]
      "failed" [:div.box.mt-1  {:style {:background-color "#F6EDC2"}}
                (for [item [[{:margin 3 :label "Message"}
                             {:margin 0 :label (str "Requestor: " requestor)}]
                            [{:margin 3 :label "Requested"} {:margin 0 :label ""}]
                            [{:margin 3 :label "Deadline"} {:margin 0 :label ""}]
                            [{:margin 3 :label "Expired"} {:margin 0 :label ""}]]]
                  ^{:key (str "at11 -" (rand 10000000))}
                  [card-grid item])]
      
      "completed" [:div.box.mt-1  {:style {:background-color "#F6EDC2"}}
                   (for [item [[{:margin 3 :label "Message"}
                                {:margin 0 :label (str "Requestor: " requestor)}
                                {:margin 3 :label [:a {:href "/"} "Transcript"]}]
                               [{:margin 3 :label "Requested"} {:margin 0 :label ""}]
                               [{:margin 3 :label "Requested"} {:margin 0 :label ""}]
                               [{:margin 3 :label "Completed"} {:margin 0 :label ""}]]]
                     ^{:key (str "at11 -" (rand 10000000))}
                     [card-grid item])])))

(defn earnings-log [status requestor]
  (fn []
    (case status
      "earned" [:div.box.mt-1 {:style {:background-color "#F6EDC2"}}
                (for [item [[{:margin 3 :label "Chat"}
                             {:margin 0 :label (str "Requestor: " requestor)}]
                            [{:margin 3 :label "Completed"} {:margin 0 :label ""}]
                            [{:margin 3 :label "Fee"} {:margin 0 :label "$15"}]
                            [{:margin 3 :label "Balance"} {:margin 0 :label "$12"}]]]
                  ^{:key (str "at11 -" (rand 10000000))}
                  [card-grid item])]
      "refund" [:div.box.mt-1  {:style {:background-color "#F6EDC2"}}
                (for [item [[{:margin 3 :label "Chat"}
                             {:margin 0 :label (str "Provider: " requestor)}]
                            [{:margin 3 :label "Completed"} {:margin 0 :label ""}]
                            [{:margin 3 :label "Refund"} {:margin 0 :label "$15"}]
                            [{:margin 3 :label "Balance"} {:margin 0 :label "$12"}]]]
                  ^{:key (str "at11 -" (rand 10000000))}
                  [card-grid item])]
      "paid" [:div.box.mt-1  {:style {:background-color "#F6EDC2"}}
              (for [item [[{:margin 3 :label "Chat"}
                           {:margin 0 :label (str "Provider: " requestor)}]
                          [{:margin 3 :label "Completed"} {:margin 0 :label ""}]
                          [{:margin 3 :label "Billed"} {:margin 0 :label "$15"}]
                          [{:margin 3 :label "Balance"} {:margin 0 :label "$12"}]]]
                ^{:key (str "at11 -" (rand 10000000))}
                [card-grid item])])))

(defn profile-item [items]
  [:div.columns.is-centered.is-mobile
   (for [item items]
     ^{:key (str "at11 -" (rand 10000000))}
     (case (:margin item)
       0 [:div.column (:label item)]
       [(keyword (str "div.column." "is-" (:margin item) ".has-text")) (:label item)]))])

(defn profile []
  (fn []
      [:div.ml-2
       [:div.columns.is-centered
        [:div.column.is-8
         [:div.title.is-6.has-text "Profile"]]]
       (for [item [[{:margin 3 :label "LinkedIn Profile : "}
                    {:margin 0 :label [:button.button
                                       [:a {:href ""} "Import"]]}]
                   [{:margin 3 :label "Last Updated : "}
                    {:margin 0 :label "Jan 20 2021"}]
                   [{:margin 3 :label "Photo : "}
                    {:margin 0 :label [:figure]}]
                   [{:margin 3 :label "Work Experience : "}
                    {:margin 0 :label ""}]
                   [{:margin 3 :label "Internship : "}
                    {:margin 0 :label ""}]
                   [{:margin 3 :label "Scholarship : "}
                    {:margin 0 :label ""}]
                   [{:margin 3 :label "Publications : "}
                    {:margin 0 :label ""}]
                   [{:margin 3 :label "Patents : "}
                    {:margin 0 :label ""}]
                   [{:margin 3 :label "Membership : "}
                    {:margin 0 :label ""}]
                   [{:margin 3 :label "Courses : "}
                    {:margin 0 :label ""}]
                   [{:margin 3 :label "Competitions : "}
                    {:margin 0 :label ""}]
                   [{:margin 3 :label "Recommendations : "}
                    {:margin 0 :label ""}]
                   [{:margin 3 :label "Update : "}
                    {:margin 0 :label [:button.button
                                       [:a {:href ""} "Update"]]}]]]
         ^{:key (str "at11 -" (rand 10000000))}
         [profile-item item])]))



(defn account-detail [item]
  (fn []
    (case item
      "myservice" [:div [:div.mb-2 "For maximum benefit, display your service link in your LinkedIn profile, your Facebook page,all your websites, and in your email signature"]
                   [:div "Here is your link : URL"]]
      "messages" [:div [:label [:input {:type "radio" :name "answer" :style {:margin "0% 1% 0% 0%"}}] "Yes"]]
      "chats" [:div [:label [:input {:type "radio" :name "answer" :style {:margin "0% 1% 0% 0%"}}] "Yes"]
               [:label [:input {:type "radio" :name "answer" :style {:margin "0% 1% 0% 2%"}}] "No"]]
      "instant-chats" [:div [:label [:input {:type "radio" :name "answer" :style {:margin "0% 1% 0% 0%"}}] "Yes"]
                       [:label [:input {:type "radio" :name "answer" :style {:margin "0% 1% 0% 2%"}}] "No"]]
      "payperuse" [:div
                   [:div.columns
                    [:div.column.is-2 "Standard"]
                    [:div.column [:div [:label [:input {:type "radio" :name "answer" :style {:margin "0% 1% 0% 0%"}}] "XM[$02]"]
                                  [:label [:input {:type "radio" :name "answer" :style {:margin "0% 1% 0% 2%"}}] "XC[$10]"]]]]
                   [:div.columns
                    [:div.column.is-2 "PPU1"]
                    [:div.column [:div
                                  [:label [:input {:type "radio" :name "answer" :style {:margin "0% 1% 0% 0%"}}] "Standard"]
                                  [:label [:input {:type "radio" :name "answer" :style {:margin "0% 1% 0% 2%"}}] "PPUI"]]]]]
      "subscriptions" [:div
                       [:div.columns
                        [:div.column.is-2 "SUB1"]
                        [:div.column [:div [:label [:input {:type "radio" :name "answer" :style {:margin "0% 1% 0% 0%"}}] "Fee[$10]"]
                                      [:label [:input {:type "radio" :name "answer" :style {:margin "0% 1% 0% 2%"}}] "Term[Month or Year]"]]]]
                       [:div.columns
                        [:div.column.is-2 "Included"]
                        [:div.column [:div [:label [:input {:type "radio" :name "answer" :style {:margin "0% 1% 0% 0%"}}] "XM[$05]"]
                                      [:label [:input {:type "radio" :name "answer" :style {:margin "0% 1% 0% 2%"}}] "XC[$01]"]]]]]
      "profile" [:div [(profile)]]
      "notifications" [:div
                       [:div.columns
                        [:div.column.is-2 "Email"]
                        [:div.column [:div [:label [:input {:type "radio" :name "answer" :style {:margin "0% 1% 0% 0%"}}] "Yes"]
                                      [:label [:input {:type "radio" :name "answer" :style {:margin "0% 1% 0% 2%"}}] "No"]]]]
                       [:div.columns
                        [:div.column.is-2 "SMS"]
                        [:div.column [:div
                                      [:label [:input {:type "radio" :name "answer" :style {:margin "0% 1% 0% 0%"}}] "Yes"]
                                      [:label [:input {:type "radio" :name "answer" :style {:margin "0% 1% 0% 2%"}}] "No"]]]]]
      "availability" [:div "Availability"]

      "chg-password" [:div "Change Password"]
      "provider-msgs" [:div 
                       [:div.mb-1 {:style {:font-weight "bold"}}
                        "You are the Provider : Messages"]
                        [(provider-message "requested" "raghu")]
                        [(provider-message "failed" "raghu")]
                        [(provider-message "completed" "raghu")]
                        [(provider-message "declined" "raghu")]
                        [(provider-message "accepted" "raghu")]]
      "provider-chats" [:div [:div.mb-1 {:style {:font-weight "bold"}}
                              "You are the Provider : Chats"]
                        [(provider-chat "requested" "raghu")]
                        [(provider-chat "failed" "raghu")]
                        [(provider-chat "completed" "raghu")]
                        [(provider-chat "declined" "raghu")]
                        [(provider-chat "accepted" "raghu")]]
      "requestor-msgs" [:div [:div.mb-1 {:style {:font-weight "bold"}} 
                              "You are the Requestor : Messages"]
                        [(requestor-message "requested" "raghu")]
                        [(requestor-message "failed" "raghu")]
                        [(requestor-message "completed" "raghu")]
                        [(requestor-message "declined" "raghu")]
                        [(requestor-message "accepted" "raghu")]]
      "requestor-chats" [:div [:div.mb-1 {:style {:font-weight "bold"}} 
                               "You are the Requestor : Chats"]
                         [(requestor-chat "requested" "raghu")]
                         [(requestor-chat "failed" "raghu")]
                         [(requestor-chat "completed" "raghu")]
                         [(requestor-chat "declined" "raghu")]
                         [(requestor-chat "accepted" "raghu")]]
      "earnings" [:div [:div.mb-1 {:style {:font-weight "bold"}} 
                        "Earnings"]
                  [(earnings-log "earned" "raghu")]
                  [(earnings-log "refund" "raghu")]
                  [(earnings-log "paid" "raghu")]]
      "payment" [:div "Payment Details"])))

(defn services-page []
  (fn []
    [:div.section
       [:div.container.is-fluid.mt-5.pt-5
        [:div.columns.is-centered
          [:div.column.is-8
           [:div.title.is-6.has-text 
            "Profile Setup"]]]
        (for [item [[{:margin 3 :label "LinkedIn Profile : "}
                     {:margin 0 :label [:button.button
                                        [:a {:href ""} "Import"]]}]
                    [{:margin 3 :label "Last Updated : "}
                     {:margin 0 :label "Jan 20 2021"}]
                    [{:margin 3 :label "Photo : "}
                     {:margin 0 :label [:figure]}]
                    [{:margin 3 :label "Work Experience : "}
                     {:margin 0 :label ""}]
                    [{:margin 3 :label "Internship : "}
                     {:margin 0 :label ""}]
                    [{:margin 3 :label "Scholarship : "}
                     {:margin 0 :label ""}]
                    [{:margin 3 :label "Publications : "}
                     {:margin 0 :label ""}]
                    [{:margin 3 :label "Patents : "}
                     {:margin 0 :label ""}]
                    [{:margin 3 :label "Membership : "}
                     {:margin 0 :label ""}]
                    [{:margin 3 :label "Courses : "}
                     {:margin 0 :label ""}]
                    [{:margin 3 :label "Competitions : "}
                     {:margin 0 :label ""}]
                    [{:margin 3 :label "Recommendations : "}
                     {:margin 0 :label ""}]
                    [{:margin 3 :label "Update : "}
                     {:margin 0 :label [:button.button
                                        [:a {:href ""} "Update"]]}]]]
          ^{:key (str "at11 -" (rand 10000000))}
          [profile-item item])
        
        ]]))

(defn account [] 
  (let [state (r/atom {:selected "provider-msgs"})]
    (fn []
      
      [:div.section
       [:div.container.is-fluid.mt-5.pt-5
        [:div.columns.is-mobile
         [:div.column.is-2
          [:> ui/Menu {:vertical true :fluid true :color "teal"}
           [:> ui/MenuItem
            [:> ui/MenuHeader "Provider Log"]
            [:> ui/MenuMenu {:vertical true :fluid true :color "teal"}
             [:> ui/MenuItem {:on-click #(swap! state assoc-in [:selected] "provider-msgs")} "Messages"]
             [:> ui/MenuItem {:on-click #(swap! state assoc-in [:selected] "provider-chats")} "Chats"]]]
           [:> ui/MenuItem
            [:> ui/MenuHeader "Requestor Log"]
            [:> ui/MenuMenu {:vertical true :fluid true :color "teal"}
             [:> ui/MenuItem {:on-click #(swap! state assoc-in [:selected] "requestor-msgs")} "Messages"]
             [:> ui/MenuItem {:on-click #(swap! state assoc-in [:selected] "requestor-chats")} "Chats"]]]
           [:> ui/MenuItem
            [:> ui/MenuHeader "Services"]
            [:> ui/MenuMenu {:vertical true :fluid true :color "teal"}
             [:> ui/MenuItem {:on-click #(swap! state assoc-in [:selected] "myservice")} "My Service"]
             [:> ui/MenuItem {:on-click #(swap! state assoc-in [:selected] "messages")} "Messages"]
             [:> ui/MenuItem {:on-click #(swap! state assoc-in [:selected] "chats")} "Chats"]
             [:> ui/MenuItem {:on-click #(swap! state assoc-in [:selected] "instant-chats")} "Instant Chats"]
             [:> ui/Dropdown {:item true :text "Plans"}
              [:> ui/DropdownMenu
               [:> ui/DropdownItem {:on-click #(swap! state assoc-in [:selected] "payperuse") :text "Pay Per Use"}]
               [:> ui/DropdownItem {:on-click #(swap! state assoc-in [:selected] "subscriptions") :text "Subscriptions"}]]]]]
           [:> ui/MenuItem
            [:> ui/MenuHeader "Settings"]
            [:> ui/MenuMenu {:vertical true :fluid true :color "teal"}
             [:> ui/MenuItem {:on-click #(swap! state assoc-in [:selected] "profile")} "Profile"]
             [:> ui/MenuItem {:on-click #(swap! state assoc-in [:selected] "notifications")} "Notifications"]
             [:> ui/MenuItem {:on-click #(swap! state assoc-in [:selected] "chg-password")} "Change Password"]
             [:> ui/MenuItem {:on-click #(swap! state assoc-in [:selected] "availability")} "Availability"]]]
           [:> ui/MenuItem {:on-click #(swap! state assoc-in [:selected] "payment")} "Payment Method"]
           [:> ui/MenuItem {:on-click #(swap! state assoc-in [:selected] "earnings")} "Earning Log"]]]


         [:div.column.mt-3 {:style {:background-color "#ffffff" :min-height "600px"}} 
          [(account-detail (:selected @state))]]]]])))

(defn about-page []
  (fn []
    [:div.columns.is-fluid.mt-5.pt-5
     [:div.column.is-4]
     [:div.column.is-4 (str "About Page")]
     [:div.column.is-4]]
    )
  )

(defn main-panel []
  (let [name (re-frame/subscribe [::subs/name])]
    [:div.container
     [:div.columns.is-centered
      [:div.column.is-12 [navbar]]]
     [home-page]]))
