Ext.define('uio.UioForm',
			{
				extend: 'Ext.form.Panel',
				requires: ['Ext.field.Number'],
				xtype: 'uioform',
				allowOnlyWhitespace: false,
				masked: {xtype: 'loadmask', message: 'Loading'},
				
				
				constructor: function(config) {
					config.items = uio.formdefs[config.jclass].items;
					config.buttons = uio.formdefs[config.jclass].buttons;
					
					config.items.push({xtype: 'container', items: config.buttons});
					
					config.title = uio.formdefs[config.jclass].title;


					config.url = '../../'+config.jclass+'/submit.htm';
					
					console.log("items: %o", config.items);
					
					this.callParent(arguments);
			    },
			    load: function(config)
			    {
			    	if (typeof(config)!=="object")
		    		{
		    			config = {};
		    		}
			    	config.url = config.url || '../../'+this.jclass+"/load.json";
			    	config.method = config.method || 'get';
			    	var f = this.callParent([config]);
			    },
			    
			    initialize: function()
			    {
			    	this.callParent();
			    	if (this.loadObject)
			    	{
			        	this.load(this.loadObject);
			    	}
			    }
				
			});
