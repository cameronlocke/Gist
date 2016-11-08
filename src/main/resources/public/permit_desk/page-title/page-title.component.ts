import { Component, Input, OnInit } from '@angular/core';
import { MenuModule,MenuItem, Button} from 'primeng/primeng';

@Component ({
  moduleId: module.id,
  selector: 'page-title',
  templateUrl: 'page-title.component.html'
})

export class PageTitleComponent implements OnInit {

   @Input()
   public title : string;

   @Input()
   public access_level : string;
   
   
   public disabled:boolean = false;
   public status:{isopen:boolean} = {isopen: false};
   

   private profileItems: MenuItem[];

   private adminItems: MenuItem[];

    // Initialise 
    
    ngOnInit() {
    
        this.profileItems = [
                    {label: 'Change Password'},
                    {label: 'Contact Details', icon: 'fa-refresh'},
                    {label: 'Sign-out'}
                ];

        this.adminItems = [
                    {label: 'User Management'},
                    {label: 'Conditions'},
                    {label: 'Condition Triggers'},
                    {label: 'About'}
                ];
    }
   
}