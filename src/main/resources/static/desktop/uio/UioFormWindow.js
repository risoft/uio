Ext.define('uio.dekstop.UioFormWindow',
    			{
    				extend: 'Ext.window.Window',
    				requires: [ 'uio.UioForm'],
    				xtype: 'uioformwindow',
    				constructor: function(config) {
    					
    					config.items = [{xtype: 'uioform', jclass: config.jclass, noTitle: true}];
    					config.title = uio.formdefs[config.jclass].title ;

    					
    					this.callParent(arguments);
    			    },
    				
    			}, function(){});
