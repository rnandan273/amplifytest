(ns cljapp.views
  (:require
   [re-frame.core :as re-frame]
   [cljapp.events :as events]
   [cljapp.subs :as subs]))

(defn navbar []
  [:div.columns.is-centered.is-vcentered.is-mobile
   [:div.column [:a {:href "/#/"} "GIGION"]]
   [:div.column [:a {:href "/#/"} "HOME PAGE"]]
   [:div.column

    [:div.level.is-mobile {:style {:padding "2% 2% 2% 2%"}}
     [:div.level-left]
     [:div.level-right
      [:div.level-item [:a {:href "/#/about"} "About"]]
      [:div.level-item [:a {:href "/#/login"} "Sign In"]]]]]])

(defn home-page []
  [:div
   [:div.box {:style {:margin-top "2%" :padding "0% 10% 4% 10%" :border-color "black"}}

    [:div.columns.is-centered.is-vcentered
     [:div.column
      [:div.title.is-8.has-text-info {:style {:text-align "center"}}
       (str "Welcome to the Worlds first micro-consulting platform")]]]

    [:div.columns.is-centered.is-vcentered
     [:div.column
      [:div.title.is-5 {:style {:text-align "center"}}
       (str "Your expertise can help someone, somewhere in the world and we can help you to monetize your expertise")]]]

    [:div.columns.is-centered.is-vcentered
     [:div.column
      [:div.title.is-6 {:style {:text-align "center" :margin-top "-2%"}}
       (str "Please ") [:a {:href "/#/signup"} "click here"] (str " to sign up as a provider")]]]

    [:div.columns.is-centered.is-vcentered
     [:div.column {:style {:color "black"}}
      [:div.title.is-5 {:style {:text-align "center"}}
       (str "Are you looking at a Job at a certain company?")]]]
    [:div.columns.is-centered.is-vcentered
     [:div.column
      [:div.title.is-6 {:style {:text-align "center" :margin-top "-2%"}}
       (str "Please ") [:a {:href "/#/signup"} "click here"] (str " and we can connect you with an employee at that company that can help you")]]]

    [:div.columns.is-centered.is-vcentered
     [:div.column
      [:div.title.is-5 {:style {:text-align "center"}}
       (str "Are you planning to pursue higher education at a certain college?")]]]
    [:div.columns.is-centered.is-vcentered
     [:div.column
      [:div.title.is-6 {:style {:text-align "center" :margin-top "-2%"}}
       (str "Please ") [:a {:href "/#/signup"} "click here"] (str " and we can connect you with an current or former student or faculty at that college who can answer all your questions")]]]

    [:div.columns.is-centered.is-vcentered
     [:div.column
      [:div.title.is-5 {:style {:text-align "center"}}
       (str "Are you a Recruiter?")]]]
    [:div.columns.is-centered.is-vcentered
     [:div.column
      [:div.title.is-6 {:style {:text-align "center" :margin-top "-2%"}}
       (str "Please ") [:a {:href "/#/signup"} "click here"] (str " and we can connect you with employees at any company that you want to target")]]]

    [:div.columns.is-centered.is-vcentered
     [:div.column
      [:div.title.is-5 {:style {:text-align "center"}}
       (str "Are you a Private Tutor?")]]]
    [:div.columns.is-centered.is-vcentered
     [:div.column
      [:div.title.is-6 {:style {:text-align "center" :margin-top "-2%"}}
       (str "Please ") [:a {:href "/#/signup"} "click here"] (str " and we can help streamline and monetize your outside the classroom communications with all your students")]]]


    [:div.columns.is-centered.is-vcentered
     [:div.column
      [:div.title.is-5 {:style {:text-align "center"}}
       (str "Are you a Doctor?")]]]
    [:div.columns.is-centered.is-vcentered
     [:div.column
      [:div.title.is-6 {:style {:text-align "center" :margin-top "-2%"}}
       (str "Please ") [:a {:href "/#/signup"} "click here"] (str " and we can help streamline and monetize your outside the clinic communications with all your patients")]]]]])

(defn login-page []
  [:section.hero.is-fullheight {:style {:background-color "#fff7e6"}}
   [:div.hero
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
          [:a {:href "/#/user-account-service-log"} "Sign In"]]]]]]
     [:div.column]]]])

(defn signup-page []
  [:section.hero.is-fullheight {:style {:background-color "#fff7e6"}}
   [:div.hero-body
    [:div.container
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
         [:label.label "Time Zone"]
         [:input.input {:type "text" :placeholder "Time Zone"}]]
        [:div.field
         [:label.checkbox
          [:input {:type "checkbox"}]
          "I have read and agree with your"
          [:a {:href "#"} "terms of service"]
          [:a {:href "#"} "privacy policy"]]]
        [:div.columns.is-centered.is-mobile
         [:div.column.is-offset-1
          [:button.button
           [:a {:href "/#/"} "Back"]]]
         [:div.column.is-offset-1
          [:button.button
           [:a {:href "/#/user-account-services"} "Sign Up"]]]]]]]]]])

(defn service-log-page []
  [:section.hero.is-fullheight {:style {:background-color "#fff7e6"}}
   [:div.hero-body
    [:div.container
     [:div.columns.is-mobile {:style {:margin-bottom "50%"}}
    
  			 [:div.column
       [:label.label [:a "Service Log"]]
       [:h4 "Chat"] [:div "Requested"]
       [:div "Scheduled"]]]
      ]]])

(defn services-page []
  [:section.hero.is-fullheight {:style {:background-color "#fff7e6"}}
   [:div.hero-body
    [:div.container
     [:div.columns.is-centered.is-vcentered
      [:div.column.is-5
       [:label.label [:a "My Service Link"]]
       [:div "For maximum benefit, display your service link in your LinkedIn profile, 
          	   your Facebook page,all your websites, and in your email signature"]]]

     [:div.columns.is-mobile  {:style {:margin-bottom "20%"}}
      [:div.column.is-2
       [:div.tile.is-vertical
        [:div.tile
         [:button.button
          [:a {:href "/#/"} "Messages"]]]
        [:div.tile
         [:button.button
          [:a {:href "/#/"} "Chat"]]]
        [:div.tile
         [:button.button
          [:a {:href "/#/"} "Instant Chat"]]]
        [:div.tile
         [:button.button
          [:a {:href "/#/"} "Plans"]]]]]
  		  
      [:div.column
       [:div.control "Message"
        [:label.radio [:input {:type "radio" :name "answer"}] "Yes"]]
       [:div.control "Chat"
        [:label.radio [:input {:type "radio" :name "answer"}] "Yes"]
        [:label.radio [:input {:type "radio" :name "answer"}] "No"]]
       [:div.control "Enable Instant Chat"
        [:label.radio [:input {:type "radio" :name "answer"}] "Yes"]
        [:label.radio [:input {:type "radio" :name "answer"}] "No"]]
       [:label.label "Pay Per Use Rate Plan</label"]
       [:div "Standard (Default) : "]
       [:div "PPU1 : "]
       [:label.label "Subscription Rate Plan"]
       [:div "SUB1 : "]
       [:div "Included : "]
       [:label.label "My Groupd"]
       [:button.button "Add New Group"]]]]]])

(defn about-page []
  [:div.columns.is-vcentered.is-centered {:style {:background-color "#fff7e6"}}
   [:div.column.is-4]
   [:div.column.is-4 (str "About Page")]
   [:div.column.is-4
     ]
    ])

(defn main-panel []
  (let [name (re-frame/subscribe [::subs/name])]
    [:div.container
     [:div.columns.is-centered
      [:div.column.is-12 [navbar]]]
     [home-page]]))
