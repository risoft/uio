Ext.define('uio.desktop.UioForm',
    			{
    				extend: 'Ext.form.Panel',
    				xtype: 'uioform',
    				requires: ['uio.desktop.UioCombo'],
    				allowOnlyWhitespace: false,
    				constructor: function(config) {
    					config.items = uio.formdefs[config.jclass].items;
    					config.buttons = uio.formdefs[config.jclass].buttons;
    					config.title = uio.formdefs[config.jclass].title;

            	    	config.defaultType ='textfield';

            	    	config.url = config.jclass+'/submit.htm';
    					this.callParent(arguments);
    			    },
    			    load: function(config)
    			    {
    			    	if (typeof(config)!=="object")
			    		{
			    			config = {};
			    		}
    			    	config.url = config.url || this.jclass+"/load.json";
    			    	config.method = config.method || 'get';
    			    	var f = this.callParent([config]);
    			    },
    			    
    			    initComponent: function()
    			    {
    			    	this.callParent();
    			    	if (this.loadObject)
    			    	{
    			        	this.load(this.loadObject);
    			    	}
    			    }
    				
    			});
