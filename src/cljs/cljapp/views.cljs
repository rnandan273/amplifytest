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

(defn account []
  [:div.section
   [:div.container.is-fluid.mt-5.pt-5
     [:div.level.is-mobile
      [:div.level-left 
       [:div.level-item 
       [:> ui/Menu {:vertical true}
       [:> ui/MenuItem
         [:> ui/MenuHeader "Accounts"]
          [:> ui/MenuMenu
           [:> ui/MenuItem {:on-click #()} "Settings"]
           [:> ui/MenuItem {:on-click #()}"Plans"]]]
       [:> ui/MenuItem
        [:> ui/MenuHeader "Messages"]
        [:> ui/MenuMenu
         [:> ui/MenuItem {:on-click #()}"Requested"]
         [:> ui/MenuItem {:on-click #()}"Scheduled"]
         [:> ui/MenuItem {:on-click #()}"Create"]]]
       [:> ui/MenuItem
        [:> ui/MenuHeader "Chats"]
        [:> ui/MenuMenu
         [:> ui/MenuItem {:on-click #()}"Requested"]
         [:> ui/MenuItem {:on-click #()}"Scheduled"]
         [:> ui/MenuItem {:on-click #()}"Create"]]]]]]
      
     [:div.level-item {:style {:background-color "white"}} "Details"]]]])


 
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
     "and we can help streamline and monetize your outside the clinic communications with all your patients"]

     ]])

(defn login-page []
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
     [:div.column]]]])


(defn signup-page []
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
         [:label.label "Time Zone"]
         [:input.input {:type "text" :placeholder "Time Zone"}]]
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
           [:a {:href "/#/user-account-services"} "Sign Up"]]]]]]]]])

(defn service-log-page []
  [:section.hero
   [:div.hero-body
    [:div.container
     [:div.columns.is-mobile {:style {:margin-bottom "50%"}}
    
  			 [:div.column
       [:label.label [:a "Service Log"]]
       [:h4 "Chat"] [:div "Requested"]
       [:div "Scheduled"]]]
      ]]])

(defn services-page []
  [:div.section
   [:div.container.is-fluid.mt-5.pt-5
     [:div.level.is-mobile
      [:div.level-left 
       [:div.level-item 
       [:> ui/Menu {:vertical true}
        [:> ui/MenuItem
         [:> ui/MenuHeader "My Service Link"]
         [:> ui/MenuMenu
          [:> ui/MenuItem {:on-click #()} "Details"]]]
        [:> ui/MenuItem
         [:> ui/MenuHeader "Messages"]
         [:> ui/MenuMenu
          [:> ui/MenuItem {:on-click #()} "Select"]]]
        [:> ui/MenuItem
         [:> ui/MenuHeader "Chats"]
         [:> ui/MenuMenu
          [:> ui/MenuItem {:on-click #()} "Select"]]]
        [:> ui/MenuItem
         [:> ui/MenuHeader "Subscription Rates"]
         [:> ui/MenuMenu
          [:> ui/MenuItem {:on-click #()} "Select"]]]]]]

      
       [:div.level-item {:style {:background-color "white"}} "Details"]]]])



(defn about-page []
  [:div.columns.is-fluid.mt-5.pt-5
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
